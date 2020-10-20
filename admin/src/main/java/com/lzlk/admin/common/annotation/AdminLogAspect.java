package com.lzlk.admin.common.annotation;

import com.alibaba.fastjson.JSONObject;
import com.lzlk.admin.common.util.AdminUtil;
import com.lzlk.base.utils.request.RequestUtil;
import com.lzlk.dao.mybatis.admin.bean.AdminRequestLogDo;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import com.lzlk.mysql.manager.admin.AdminRequestLogManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/24 20:59
 * @Created by 湖南达联
 */
@Aspect
@Component
@Slf4j
public class AdminLogAspect {

    @Resource
    private AdminRequestLogManager adminRequestLogManager;

    @Pointcut(value = "@annotation(com.lzlk.admin.common.annotation.AdminLog)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();

        AdminRequestLogDo adminRequestLog = new AdminRequestLogDo();
        adminRequestLog.setAdminUserId(loginUser == null ? 0L : loginUser.getId());
        adminRequestLog.setRequestUrl(RequestUtil.getRequestPath(request));
        adminRequestLog.setSignIp(RequestUtil.getClientIP(request));
        Object[] args = joinPoint.getArgs();
        List<Object> argss = Arrays.stream(args).filter(bean -> !(bean instanceof HttpServletRequest) && !(bean instanceof HttpServletResponse))
                .collect(Collectors.toList());
        adminRequestLog.setArgs(JSONObject.toJSONString(argss));
        adminRequestLogManager.addLog(adminRequestLog);
    }
}
