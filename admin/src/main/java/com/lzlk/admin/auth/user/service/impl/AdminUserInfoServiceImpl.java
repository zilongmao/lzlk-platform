package com.lzlk.admin.auth.user.service.impl;



import com.github.pagehelper.PageInfo;
import com.lzlk.admin.auth.role.service.AdminPermissionService;
import com.lzlk.admin.auth.role.service.AdminRoleInfoService;
import com.lzlk.admin.auth.user.service.AdminUserInfoService;
import com.lzlk.admin.auth.user.vo.*;
import com.lzlk.admin.common.util.AdminUtil;
import com.lzlk.admin.common.util.ShiroMD5Utils;
import com.lzlk.admin.exception.AdminException;
import com.lzlk.admin.exception.AdminExceptionEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.spring.SpringCglibBeanUtils;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import com.lzlk.mysql.manager.admin.AdminUserInfoManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/18 17:01
 * @Created by 湖南达联
 */
@Service
public class AdminUserInfoServiceImpl implements AdminUserInfoService {


    @Resource
    private AdminUserInfoManager adminUserInfoManager;

    @Resource
    private AdminRoleInfoService adminRoleInfoService;

    @Resource
    private AdminPermissionService adminPermissionService;

    @Override
    public Result<Object> loginSuccess(String loginName, String ip) {
        AdminUserInfoDo userByLoginName = this.findUserByLoginName(loginName);
        AdminUtil.setLoginUser(userByLoginName);
        adminUserInfoManager.updateUserLoginIp(loginName, ip);
        AdminUserMenuVo adminUserMenuVo = new AdminUserMenuVo();
        adminUserMenuVo.setAdminUserVo(SpringCglibBeanUtils.convert(userByLoginName, AdminUserVo.class));
        adminUserMenuVo.setMenuVoList(adminPermissionService.getLoginUserMenu(userByLoginName.getId()));
        return ResultFactory.success(adminUserMenuVo);
    }


    @Override
    public AdminUserInfoDo findUserByLoginName(String loginName) {
        return adminUserInfoManager.findUserByLoginName(loginName);
    }

    @Override
    public AdminUserInfoDo findUserById(Long userId) {
        return adminUserInfoManager.findUserById(userId);
    }

    @Override
    public Result<Object> addAdminUserInfo(AddUserInfoVo vo, Long loginUserId) {
        AdminUserInfoDo userByLoginName = this.findUserByLoginName(vo.getLoginName());

        if(userByLoginName != null){
            throw new AdminException(AdminExceptionEnums.ADMIN_LOGIN_NAME_EXCEPTION.getCode());
        }

        AdminUserInfoDo newUserInfo = SpringCglibBeanUtils.convert(vo,AdminUserInfoDo.class);
        newUserInfo.setPassWord(ShiroMD5Utils.encryptPassword(vo.getLoginName(),vo.getPassword()));
        newUserInfo.setCreateUserId(loginUserId);
        newUserInfo.setUpdateUserId(loginUserId);
        newUserInfo.setMaxWeight(0);
        adminUserInfoManager.addUserInfo(newUserInfo);

        return ResultFactory.success(vo.getPassword());
    }

    @Override
    public Result<Object> updateAdminUserInfoState(String loginName,Long loginUserId,Integer isDelete) {

        AdminUserInfoDo userByLoginName = this.findUserByLoginName(loginName);

        if(userByLoginName == null){
            throw new AdminException(AdminExceptionEnums.ADMIN_USER_NOT_FIND_EXCEPTION.getCode());
        }

        //检查用户权限
        adminRoleInfoService.checkUserRoleWeight(loginUserId,userByLoginName.getId());

        adminUserInfoManager.removeAdminUserInfo(loginName,loginUserId,isDelete);

        return ResultFactory.success();
    }



    @Override
    public Result<Object> updatePassWord(AdminUpdatePassWordVo vo) {
        AdminUserInfoDo userInfoDo = AdminUtil.getLoginUser();
        if(!ShiroMD5Utils.encryptPassword(userInfoDo.getLoginName(),vo.getOldPassWord()).equals(userInfoDo.getPassWord())){
            throw new AdminException(PublicExceptionCodeEnum.EX_LOGIN_USER_PWD_ERROR.getCode());
        }
        userInfoDo.setId(userInfoDo.getId());
        userInfoDo.setUpdateUserId(userInfoDo.getId());
        userInfoDo.setUpdateTime(new Date());
        userInfoDo.setPassWord(ShiroMD5Utils.encryptPassword(userInfoDo.getLoginName(),vo.getNewPassWord()));
        adminUserInfoManager.updateAdminUserInfoById(userInfoDo);
        return ResultFactory.success();
    }


    @Override
    public Result<Object> refreshUserPassWord(String loginName,Long loginUserId) {
        AdminUserInfoDo userByLoginName = this.findUserByLoginName(loginName);
        if(userByLoginName == null){
            throw new AdminException(AdminExceptionEnums.ADMIN_USER_NOT_FIND_EXCEPTION.getCode());
        }
        //检查用户权限
        adminRoleInfoService.checkUserRoleWeight(loginUserId,userByLoginName.getId());
        String passWord = StringUtils.getRandomString();
        userByLoginName.setPassWord(ShiroMD5Utils.encryptPassword(userByLoginName.getLoginName(),passWord));
        userByLoginName.setUpdateTime(new Date());
        userByLoginName.setUpdateUserId(loginUserId);
        adminUserInfoManager.updateAdminUserInfoById(userByLoginName);
        return ResultFactory.success(passWord);
    }

    @Override
    public Result<Object> findUserInfoPage(Long userId, String loginName, Integer pageNo,Integer isDelete) {
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        PageInfo<AdminUserInfoDo> adminUserPage = adminUserInfoManager.findAdminUserPage(userId, loginName,loginUser.getMaxWeight(), pageNo,isDelete);
        PageVo page = new PageVo();
        page.setDataList(SpringCglibBeanUtils.convertByList(adminUserPage.getList(), AdminUserVo.class));
        page.setTotal(adminUserPage.getTotal());
        return ResultFactory.success(page);
    }

    @Override
    public Result<Object> checkLoginName(String loginName) {
        return  ResultFactory.success(this.findUserByLoginName(loginName)== null ? false:true);
    }

    @Override
    public void updateUserWeight(Long userId,Long loginUserId, Integer weight) {
        adminUserInfoManager.updateUserWeight(userId,loginUserId,weight);
    }
}
