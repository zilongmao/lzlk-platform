package com.lzlk.main.business.sign.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzlk.base.annotation.auth.bean.UserAuthLogin;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.enums.RequestTypeEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.aes.AESUtil;
import com.lzlk.base.utils.jwt.JwtTokenUtil;
import com.lzlk.base.utils.request.RequestUtil;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.main.business.sign.service.SignService;
import com.lzlk.main.business.sign.vo.LoginAesData;
import com.lzlk.main.business.sign.vo.SignDataVo;
import com.lzlk.main.business.sign.vo.WeChatLoginParamVo;
import com.lzlk.main.exception.MainException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 邻座旅客
 * @Description
 * @Date 2019/4/22 19:43
 * @Created by 湖南达联
 */
@Controller
@RequestMapping("/{source}/sign")
@Api(tags = "登录注册相关")
@Slf4j
public class SignController {


    @Resource
    private SignService signService;

//    @Value("${app.auth.mobile}")
//    private String appAuthMobile;

//    @Value("${app.auth.code}")
//    private String appAuthCode;

    @GetMapping(value = {"/refreshToken/{version:.+}", "/refreshToken"})
    @ResponseBody
    @ApiOperation(value = "刷新Token")
    public Result<Object> refreshToken(HttpServletRequest request, @PathVariable String source,
                                       @PathVariable(required = false) String version) {

        String token = RequestUtil.getRequestHeaderByKey(request,BaseConstants.TOKEN_KEY);
        //参数校验
        if (StringUtils.isBlank(token)) {
            log.error(RequestUtil.getErrorMsg(request) + "," + "token:" + token);
            throw new MainException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }
        String json = "";
        try {
            json = JwtTokenUtil.decryption(token, BaseConstants.JWT_TOKEN_KEY);
        } catch (Exception e) {
            log.error("token解析失败:{}",token);
            throw new MainException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }
        if (StringUtils.isBlank(json)) {
            log.error("解析token内容为空:{}",json);
            throw new MainException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }

        UserAuthLogin userAuthLogin = JSONObject.parseObject(json, UserAuthLogin.class);

        if (userAuthLogin == null || userAuthLogin.getLoginUserId() == null) {
            log.error("刷新token未找到用户,token:{}",token);
            throw new MainException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }
        //如果都成功返回新的token
        if (StringUtils.isNotBlank(version)) {
            return ResultFactory.success(SignDataVo.builder().token(signService.createSignToken(userAuthLogin.getLoginUserId(), source)).build());
        }
        return ResultFactory.success(signService.createSignToken(userAuthLogin.getLoginUserId(), source));


    }


    /**
     * 微信登录接口
     *
     * @return
     */
    @PostMapping(value = {"/weChatLogin/{version:.+}", "/weChatLogin"})
    @ResponseBody
    @ApiOperation(value = "微信登录/注册")
    public Result<Object> weChatLogin(HttpServletRequest request, @RequestBody LoginAesData data) {


        String key = RequestUtil.getRequestHeaderByKey(request, BaseConstants.HEADER_KEY);

        if (StringUtils.isBlank(key)) {
            throw new MainException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }
        //ip地址做为key,解密
        String jsonData = this.aesDecrypt(data.getData(), AESUtil.getSecretKey(key));

        //转换为对象
        WeChatLoginParamVo vo = JSONObject.parseObject(jsonData, WeChatLoginParamVo.class);

        if (null == vo) {
            throw new MainException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
        }

        return signService.weChatLogin(vo);
    }



    @GetMapping("/getWeChatLoginData")
    @ResponseBody
    @ApiOperation(value = "小程序通过临时登录凭证 code获取openid和unionid")
    public Result<Object> getWeChatLoginData(HttpServletRequest request, @PathVariable String source,
                                             @RequestParam String jsCode, @RequestParam String encryptedData, @RequestParam String iv) {

        if (!RequestTypeEnums.WECHAT_PROGRAM.getValue().equals(source)) {
            throw new MainException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }
        if (StringUtils.isBlank(jsCode) || StringUtils.isBlank(encryptedData) || StringUtils.isBlank(iv)) {
            throw new MainException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
        }

        return signService.getWeChatLoginData(jsCode, encryptedData, iv);
    }


//    @GetMapping(value = "/getWeChatAccessToken")
//    @ApiOperation(value = "微信公众号h5登录注册接口")
//    @ResponseBody
//    public Result<Object> getWeChatAccessToken(@ApiParam("微信code") @RequestParam String code) {
//        return signService.getWeChatAccess(code);
//    }

    private String aesDecrypt(String data, String secretKey) {
        if (StringUtils.isBlank(data)) {
            throw new MainException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
        }
        String jsonData = null;
        try {
            //解密失败
            jsonData = AESUtil.decrypt(data, secretKey);

        } catch (Exception e) {
            throw new MainException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }

        //如果为空，返回参数错误
        if (StringUtils.isBlank(jsonData)) {
            throw new MainException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
        }

        return jsonData;
    }


}
