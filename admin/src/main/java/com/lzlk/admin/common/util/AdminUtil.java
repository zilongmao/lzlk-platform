package com.lzlk.admin.common.util;


import com.lzlk.admin.common.annotation.constants.AdminConstants;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.Session;

import java.util.Set;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/18 17:17
 * @Created by 湖南达联
 */
public class AdminUtil {


    public static AdminUserInfoDo getLoginUser(){
        Session session = SecurityUtils.getSubject().getSession();

        if(session == null){
            return null;
        }
        return (AdminUserInfoDo)session.getAttribute(AdminConstants.LOGIN_USER_SESSION_KEY);
    }

    public static void setLoginUser(AdminUserInfoDo adminUserInfoDo){
        Session session = SecurityUtils.getSubject().getSession();

        if(session == null){
            return;
        }
        session.setAttribute(AdminConstants.LOGIN_USER_SESSION_KEY,adminUserInfoDo);
    }

    public static Set<String> getLoginUserRole(){
        SimpleAuthorizationInfo attribute = getLoginUserSimpleAuthorizationInfo();
        if(attribute == null){
            return null;
        }
        return attribute.getRoles();
    }



    public static Set<String> getLoginUserPermission(){
        SimpleAuthorizationInfo attribute = getLoginUserSimpleAuthorizationInfo();
        if(attribute == null){
            return null;
        }
        return attribute.getStringPermissions();
    }


    private static SimpleAuthorizationInfo getLoginUserSimpleAuthorizationInfo(){

        Session session = SecurityUtils.getSubject().getSession();

        if(session == null){
            return null;
        }

        return (SimpleAuthorizationInfo) session.getAttribute(AdminConstants.AUTHORIZATIONINFO);
    }
}
