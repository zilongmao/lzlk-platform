package com.lzlk.base.annotation.auth.core;


import com.alibaba.fastjson.JSONObject;
import com.lzlk.base.annotation.auth.AuthPassport;
import com.lzlk.base.annotation.auth.bean.UserAuthLogin;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.jwt.JwtTokenUtil;
import com.lzlk.base.utils.request.RequestUtil;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.base.annotation.auth.AuthPassport;
import com.lzlk.base.annotation.auth.bean.UserAuthLogin;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/**
 * @Classname 邻座旅客
 * @Description TODO
 * @Date 2019/3/14 16:03
 * @Created by 湖南达联
 */
@Slf4j
public class WebTokenCore {

    /**
     * 刷新token的校验
     */
    private static Pattern PATTERN = Pattern.compile("/sign/refreshToken/");

    private HttpServletRequest request;

    private HttpServletResponse response;

    private Object handler;

    public WebTokenCore(HttpServletRequest request, HttpServletResponse response, Object handler) {
        this.request = request;
        this.response = response;
        this.handler = handler;
    }


    public boolean jwtTokenAuthPassport() throws Exception {


        //该方法是校验是不是超类或超接口，并非校验注解
        /*    handler.getClass().isAssignableFrom(HandlerMethod.class)*/

        //校验handler是不是handlerMethod
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }
        //直接获取注解内容
        AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);

        //没有校验用户注解返回true
        if (authPassport == null) {
            return true;
        }

        //获取token
        String token = RequestUtil.getRequestHeaderByKey(request, BaseConstants.TOKEN_KEY);


        if (authPassport.validate() || StringUtils.isNotEmpty(token)) {
            if (StringUtils.isBlank(token)) {
                //属于校验失败
                //response响应
                responseWriterErrorMsg(response, PublicExceptionCodeEnum.EX_USER_NOT_LOGIN_ERROR);
                log.error("用户未登录 uri:{},token:{}", request.getRequestURI(), token);
        /*        Enumeration<String> headerNames = request.getHeaderNames();
                while (headerNames.hasMoreElements()){
                    String key = headerNames.nextElement();
                    log.error("heard key:{},value:{}",key,request.getHeader(key));
                }*/
                return false;
            }
            //如果token 存在继续校验
            //jwt解密
            String jsonObj = "";
            try {
                jsonObj = JwtTokenUtil.decryption(token, BaseConstants.JWT_TOKEN_KEY);
            } catch (Exception e) {
                //解密失败，返回失败
                responseWriterErrorMsg(response, PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST);
                log.error("非法请求 uri:{},token:{}", request.getRequestURI(), token);
                return false;
            }

            //转对象
            UserAuthLogin userAuthLogin = JSONObject.parseObject(jsonObj, UserAuthLogin.class);
            //解密不出来或者失效了
            boolean timeOut = userAuthLogin.getTimeOut() < System.currentTimeMillis();

            if (timeOut && PATTERN.matcher(request.getRequestURI()).find()) {
                //设置为未超时
                timeOut = false;
            }


            if (userAuthLogin == null || timeOut) {
                //响应response
                responseWriterErrorMsg(response, PublicExceptionCodeEnum.EX_USER_NOT_LOGIN_ERROR);
                log.error("用户未登录 uri:{},token:{}", request.getRequestURI(), token);
                return false;
            }
            //存入用户id
            Long userUniId = userAuthLogin.getLoginUserId();
            request.setAttribute(BaseConstants.REQUEST_LOGIN_USER_ID_KET, userUniId);
        }
        return true;

    }

    public static void main(String[] args) throws Exception {
        String jsonData = JwtTokenUtil.decryption("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJib2R5Ijoie1wibG9naW5Vc2VySWRcIjozMDkyNzIwNDkxNTc5OTY1NDQsXCJ0aW1lT3V0XCI6MTU3NTM3MTgwMjUyMH0ifQ.DaYMrjr8oaUgbxXFS7lZ0m5oZvQaDAUgS3Dx-3HxgVg", BaseConstants.JWT_TOKEN_KEY);
        System.out.println(jsonData);

    }


    /**
     * 返回前台错误信息
     */
    public static void responseWriterErrorMsg(HttpServletResponse response, PublicExceptionCodeEnum publicExceptionCodeEnum) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Result<Object> rs = ResultFactory.failure(publicExceptionCodeEnum.getCode(),
                    publicExceptionCodeEnum.getMsg());
            out.append(JSONObject.toJSONString(rs));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }
}