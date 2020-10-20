package com.lzlk.admin.shiro;

import com.lzlk.admin.auth.role.service.AdminRoleInfoService;
import com.lzlk.admin.auth.user.service.AdminUserInfoService;
import com.lzlk.admin.common.annotation.constants.AdminConstants;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.dao.mybatis.admin.bean.AdminPermissionInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRolePermissionInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import com.lzlk.mysql.manager.admin.AdminPermissionManager;
import com.lzlk.mysql.manager.admin.AdminRolePermissionManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/18 14:55
 * @Created by 湖南达联
 */
public class AdminRealm extends AuthorizingRealm {


    @Resource
    private AdminUserInfoService adminUserInfoService;

    @Resource
    private AdminPermissionManager adminPermissionManager;

    @Resource
    private AdminRoleInfoService adminRoleInfoService;

    @Resource
    private AdminRolePermissionManager adminRolePermissionManager;

    @Value("${admin.root.login.name}")
    private String rootLoginName;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;

        String loginName = usernamePasswordToken.getUsername();
        //如果为空
        if(StringUtils.isBlank(loginName)){
            throw new UnsupportedTokenException();
        }

        //查询用户
        AdminUserInfoDo userByLoginName = adminUserInfoService.findUserByLoginName(loginName);

        //如果没有找到用户
        if(userByLoginName == null){
            throw new UnknownAccountException();
        }

        //如果用户被锁定
        if(1 == userByLoginName.getIsDelete()){
            throw new DisabledAccountException();
        }


        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userByLoginName.getLoginName(),userByLoginName.getPassWord(), ByteSource.Util.bytes(userByLoginName.getLoginName()),getName());


        return authenticationInfo;
    }

    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        CredentialsMatcher cm = this.getCredentialsMatcher();
        if (cm != null) {
            if (!cm.doCredentialsMatch(token, info)) {
                String msg = "Submitted credentials for token [" + token + "] did not match the expected credentials.";
                throw new IncorrectCredentialsException(msg);
            }
        } else {
            throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify credentials during authentication.  If you do not wish for credentials to be examined, you can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
        }

        Session session = SecurityUtils.getSubject().getSession();

        SimpleAuthorizationInfo simpleAuthorizationInfo = (SimpleAuthorizationInfo) session.getAttribute(AdminConstants.AUTHORIZATIONINFO);

        if(simpleAuthorizationInfo == null){
            simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        }

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;

        String loginName = usernamePasswordToken.getUsername();

        //查询用户
        AdminUserInfoDo userByLoginName = adminUserInfoService.findUserByLoginName(loginName);

        if(userByLoginName == null){
            throw new UnknownAccountException();
        }

        //如果用户被锁定
        if(1 == userByLoginName.getIsDelete()){
            throw new DisabledAccountException();
        }
        List<AdminRoleInfoDo> roleByUserId = null;

        //超级管理员拥有全部的角色和权限
        if(rootLoginName.equals(loginName)){
            roleByUserId = adminRoleInfoService.findAll();
        }else {
            roleByUserId = adminRoleInfoService.findRoleByUserId(userByLoginName.getId());
        }

        if(CollectionUtils.isEmpty(roleByUserId)){
            return;
        }
        List<Long> roles = roleByUserId.stream().filter(role -> role != null).map(role -> role.getId())
                .collect(Collectors.toList());

        simpleAuthorizationInfo.setRoles(roles.stream().map(role -> String.valueOf(role)).collect(Collectors.toSet()));
        if(CollectionUtils.isEmpty(roles)){
            return;
        }

        //查询角色对应的权限
        List<AdminRolePermissionInfoDo> rolePermissionByRoleIdList = adminRolePermissionManager.findRolePermissionByRoleIdList(roles);

        List<Long> permissions = rolePermissionByRoleIdList.stream().map(id -> id.getAdminPermissionId()).collect(Collectors.toList());

        if(CollectionUtils.isEmpty(permissions)){
            return;
        }
        //查询具体权限
        List<AdminPermissionInfoDo> permissionByIdList = adminPermissionManager.findPermissionByIdList(permissions,null);

        simpleAuthorizationInfo.setStringPermissions(permissionByIdList.stream().filter(permission -> StringUtils.isNotBlank(permission.getPermissionPath())).map(permission -> permission.getPermissionPath()).collect(Collectors.toSet()));

        session.setAttribute(AdminConstants.AUTHORIZATIONINFO,simpleAuthorizationInfo);




    }
}
