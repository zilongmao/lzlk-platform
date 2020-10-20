package com.lzlk.main.interceptor;


import com.lzlk.base.annotation.auth.core.WebTokenCore;
import com.lzlk.base.enums.RequestTypeEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.utils.http.HttpServletResponseUtil;
import com.lzlk.base.utils.string.SensitiveWordFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * @author 邻座旅客
 * @Description 拦截请求来源
 * @Date 2019/4/23 16:29
 * @Created by 湖南达联
 */
@Component
public class RequestSourceInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getRequestURL().toString();

        if(path.contains("swagger") || path.contains("v2/api-docs")){
           return true;
        }


        //初始化定义
        HttpServletResponseUtil.initHttpResponse(response);


        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String requestType = (String)pathVariables.get("source");

        boolean flag = RequestTypeEnums.checkVal(requestType);

        if(!flag){
            WebTokenCore.responseWriterErrorMsg(response, PublicExceptionCodeEnum.Type_MissMatch_Error);
        }
        return true;
    }

    public static void main(String[] args) {
        String string = "Nothing";
        System.out.println("待检测语句字数：" + string.length());
        long beginTime = System.currentTimeMillis();
        boolean flag =  SensitiveWordFilter.INSTANCE.isContaintSensitiveWord(string,SensitiveWordFilter.maxMatchType);
        long endTime = System.currentTimeMillis();
        System.out.println("语句中是否包含敏感词：" + flag);
        System.out.println("总共消耗时间为：" + (endTime - beginTime));
        Set<String> sensitiveWord = SensitiveWordFilter.INSTANCE.getSensitiveWord(string, SensitiveWordFilter.maxMatchType);
        System.out.println(sensitiveWord.size() +" ====== " + sensitiveWord);
        System.out.println(SensitiveWordFilter.INSTANCE.replaceSensitiveWord(string,SensitiveWordFilter.minMatchTYpe));
    }
}
