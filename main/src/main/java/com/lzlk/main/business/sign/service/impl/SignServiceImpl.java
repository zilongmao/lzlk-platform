package com.lzlk.main.business.sign.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzlk.base.annotation.auth.bean.UserAuthLogin;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.exception.enums.ExceptionSysMarkEnum;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.plugin.snowflakeid.service.ISnowflakeIdService;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.aes.AESUtil;
import com.lzlk.base.utils.http.HttpClientUtil;
import com.lzlk.base.utils.jwt.JwtTokenUtil;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.base.utils.wechat.WeChatUtils;
import com.lzlk.dao.mybatis.user.bean.UserInfoDo;
import com.lzlk.main.business.sign.service.SignService;
import com.lzlk.main.business.sign.vo.WeChatLoginParamVo;
import com.lzlk.main.exception.MainException;
import com.lzlk.mysql.manager.user.UserInfoManager;
import com.lzlk.nosql.redis.annotation.lock.SubmitLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/3/31 17 35
 * @Author: 邻座旅客
 */
@Service
@Slf4j
public class SignServiceImpl implements SignService {

    @Resource
    private UserInfoManager userInfoManager;

    @Value("${we.chat.applet.app.id}")
    private String appletAppId;

    @Value("${we.chat.applet.app.secret}")
    private String appletAppSecret;

    @Value("${we.chat.official.account.app.id}")
    private String officialAccountAppId;

    @Value("${we.chat.official.account.app.secret}")
    private String officialAccountAppSecret;

    @Resource
    private ISnowflakeIdService iSnowflakeIdService;

    private static final String USER_NAME = "旅行者_";

    private static final Integer USER_NAME_LENGTH = 8;

    @Override
    @SubmitLock(keys = "id", sysMarkEnum = ExceptionSysMarkEnum.MAIN)
    public String createSignToken(Long id, String source) {
        UserAuthLogin userAuthLogin = new UserAuthLogin();
        userAuthLogin.setLoginUserId(id);
        userAuthLogin.setTimeOut(System.currentTimeMillis() + BaseConstants.JWT_TOKEN_VALID_TIME);
        return JwtTokenUtil.createToken(JSONObject.toJSONString(userAuthLogin), BaseConstants.JWT_TOKEN_KEY);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> weChatLogin(WeChatLoginParamVo weChatLoginParamVo) {
        UserInfoDo userInfoDo = userInfoManager.queryByAppletsOpenId(weChatLoginParamVo.getWeChatOpenId());
        String signToken = "";
        // 如果用户不存在, 则注册
        if (userInfoDo == null) {
            log.info("========================= 小程序用户不存在，创建用户 =========================");
            userInfoDo = new UserInfoDo();
            userInfoDo.setId(iSnowflakeIdService.nextId());
            userInfoDo.setNickname(this.getLoginUserName(StringUtils.filterEmoji(weChatLoginParamVo.getNickname())));

//            userInfoDo.setWeChatUnionId(weChatLoginParamVo.getWeChatUnionId());
            userInfoDo.setAppletOpenId(weChatLoginParamVo.getWeChatOpenId());
            this.setUserInfoImg(userInfoDo, weChatLoginParamVo.getUserImgUrl(), weChatLoginParamVo.getUserImgUrl());
            userInfoManager.insert(userInfoDo);
        } else {
            // 如果用户是先通过关注公众号注册的, 则绑定帐号
            if (StringUtils.isEmpty(userInfoDo.getAppletOpenId())) {
                userInfoDo.setAppletOpenId(weChatLoginParamVo.getWeChatOpenId());
                userInfoManager.update(userInfoDo);
            }
        }
        signToken = this.createSignToken(userInfoDo.getId(), "wechat");
        return ResultFactory.success(signToken);
    }

    @Override
    public Result<Object> getWeChatLoginData(String jsCode, String encryptedData, String iv) {
        String url = this.builderWechatJsCodeUrl(jsCode);

        String json = HttpClientUtil.doGet(url);

        Map<String, Object> map = JSON.parseObject(json, Map.class);

        Object errorCode = map.get("errcode");


        if (errorCode != null) {
            throw new MainException(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode());
        }

        Map<String, String> resultMap = new HashMap<>();
        Object openid = map.get("openid");

        if (openid == null) {
            throw new MainException(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode());
        }
        resultMap.put("openId", openid.toString());

        String unionId = map.get("unionid") == null ? null : map.get("unionid").toString();

        if (StringUtils.isBlank(unionId)) {
            String sessionkey = map.get("session_key").toString();
            // 被加密的数据
            byte[] dataByte = Base64.decodeBase64(encryptedData);
            // 加密秘钥
            byte[] aesKeyByte = Base64.decodeBase64(sessionkey);
            // 偏移量
            byte[] ivByte = Base64.decodeBase64(iv);

            try {
                String aesResult = AESUtil.decryptWecat(dataByte, aesKeyByte, ivByte);
                Map<String, Object> aesMap = JSON.parseObject(aesResult, Map.class);
                unionId = aesMap.get("unionId") == null ? null : aesMap.get("unionId").toString();
            } catch (InvalidAlgorithmParameterException e) {
                throw new MainException(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode());
            }
        }

        resultMap.put("unionId", unionId);

        return ResultFactory.success(resultMap);
    }

    @Override
    public String oauth2buildAuthorizationUrl() {
        return null;
    }

    @Override
    public Result<Object> getWeChatAccess(String code) {
        String result = WeChatUtils.getOauth2WechatAccess(officialAccountAppId, officialAccountAppSecret, code);

        JSONObject jsonObject = JSONObject.parseObject(result);
        String openId = jsonObject.getString("openid");

        String token = jsonObject.getString("access_token");
        //获取信息
        String userInfoStr = WeChatUtils.getUserInfoApplet(token, openId);
        JSONObject infoObj = JSONObject.parseObject(userInfoStr);

        String unionId = infoObj.getString("unionid");

        if (StringUtils.isEmpty(unionId)) {
            throw new MainException(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode());
        }
        Map<String, Object> map = new HashMap<>();
        UserInfoDo userInfo = userInfoManager.queryByWeChatUnionId(unionId);
        // 如果用户存在,直接生成token
        if (userInfo != null) {
            map.put("isNewUser", 0);
            log.info("---------- 微信" + userInfo.getId() + " 用户存在，登录成功 ----------");
        } else {
            log.info("---------- 用户不存在，创建用户 ----------");
            UserInfoDo userInfoDo = new UserInfoDo();
            userInfoDo.setId(iSnowflakeIdService.nextId());
            userInfoDo.setNickname(this.getLoginUserName(StringUtils.filterEmoji(infoObj.getString("nickname"))));

            userInfoDo.setWeChatUnionId(unionId);
            userInfoDo.setOfficialAccountOpenId(openId);
            this.setUserInfoImg(userInfoDo, infoObj.getString("headimgurl"), infoObj.getString("headimgurl"));
            boolean createUserIsSuccess = userInfoManager.insert(userInfoDo);
        }
        map.put("token", this.createSignToken(userInfo.getId(), null));
        map.put("openId", openId);
        return ResultFactory.success(map);
    }

    private String getLoginUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return USER_NAME + StringUtils.getRandomString(USER_NAME_LENGTH);
        }
        return userName + "_" + StringUtils.getRandomString(6);
    }

    private void setUserInfoImg(UserInfoDo userInfoDo, String minImg, String maxImg) {
        userInfoDo.setHeadImageMin(StringUtils.isBlank(minImg) ? BaseConstants.USER_DEFAULT_HEAD_IMAGE_MIN : minImg);
        userInfoDo.setHeadImageMax(StringUtils.isBlank(maxImg) ? BaseConstants.USER_DEFAULT_HEAD_IMAGE_MAX : maxImg);
    }

    /**
     * 构造微信jscode url
     *
     * @param jsCode
     * @return
     */
    private String builderWechatJsCodeUrl(String jsCode) {
        StringBuilder stringBuilder = new StringBuilder(BaseConstants.WE_CHAT_JS_CODE_URL);
        stringBuilder.append("appid=").append(appletAppId).append("&secret=")
                .append(appletAppSecret).append("&js_code=").append(jsCode).append("&grant_type=")
                .append(BaseConstants.GRANT_TYPE);
        return stringBuilder.toString();
    }

}
