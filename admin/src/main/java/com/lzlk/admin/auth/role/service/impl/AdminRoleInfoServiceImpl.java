package com.lzlk.admin.auth.role.service.impl;

import com.github.pagehelper.PageInfo;
import com.lzlk.admin.auth.role.service.AdminRoleInfoService;
import com.lzlk.admin.auth.role.vo.AddRoleVo;
import com.lzlk.admin.auth.role.vo.AdminRoleVo;
import com.lzlk.admin.auth.user.service.AdminUserInfoService;
import com.lzlk.admin.auth.user.vo.PageVo;
import com.lzlk.admin.common.util.AdminUtil;
import com.lzlk.admin.exception.AdminException;
import com.lzlk.admin.exception.AdminExceptionEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.base.utils.spring.SpringCglibBeanUtils;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminUserRoleInfoDo;
import com.lzlk.mysql.manager.admin.AdminRoleManager;
import com.lzlk.mysql.manager.admin.AdminUserRoleManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/20 20:51
 * @Created by 湖南达联
 */
@Service
public class AdminRoleInfoServiceImpl implements AdminRoleInfoService {

    @Resource
    private AdminUserInfoService adminUserInfoService;

    @Resource
    private AdminRoleManager adminRoleManager;

    @Resource
    private AdminUserRoleManager adminUserRoleManager;

    @Override
    public Result<Object> findRoleByPage(Integer pageNo) {
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        AdminRoleInfoDo maxRoleWeight = this.getMaxRoleWeight(loginUser.getId());
        // 查询不到权限比自己大的角色
        PageInfo<AdminRoleInfoDo> roleByPage = adminRoleManager.findRoleByPage(maxRoleWeight.getRoleWeight(),pageNo);
        PageVo pageVo = new PageVo();
        pageVo.setDataList(SpringCglibBeanUtils.convertByList(roleByPage.getList(), AdminRoleVo.class));
        pageVo.setTotal(roleByPage.getTotal());
        return ResultFactory.success(pageVo);
    }

    @Override
    public AdminRoleInfoDo findRoleById(Long id) {
        return adminRoleManager.findRoleById(id);
    }

    @Override
    public Result<Object> addRole(AddRoleVo vo, Long loginUserId) {
        //这里要判断用户拥有的最高权限,不能添加比自己高的权限

        AdminRoleInfoDo maxRoleWeight = this.getMaxRoleWeight(loginUserId);

        if(maxRoleWeight == null || maxRoleWeight.getRoleWeight() <= vo.getRoleWeight()){
            throw new AdminException(PublicExceptionCodeEnum.EX_JURISDICTION_LESS.getCode());
        }

        if(this.checkRoleName(vo.getRoleName())){
            throw new AdminException(AdminExceptionEnums.ADMIN_ROLE_IS_NOT_EXCEPTION.getCode());
        }

        AdminRoleInfoDo roleInfoDo = SpringCglibBeanUtils.convert(vo,AdminRoleInfoDo.class);
        roleInfoDo.setCreateUserId(loginUserId);
        roleInfoDo.setUpdateUserId(loginUserId);
        adminRoleManager.addRole(roleInfoDo);
        return ResultFactory.success();
    }

    @Override
    public boolean checkRoleName(String roleName) {
        return adminRoleManager.checkRoleName(roleName);
    }

    @Override
    public List<AdminRoleInfoDo> findRoleByUserId(Long userId) {
        List<AdminUserRoleInfoDo> userRoleByUserId = adminUserRoleManager.findUserRoleByUserId(userId);

        if(CollectionUtils.isEmpty(userRoleByUserId)){
            return new ArrayList<>();
        }

        List<Long> roleIdList = userRoleByUserId.stream().filter(bean -> bean != null).
                map(bean -> bean.getAdminRoleId()).collect(Collectors.toList());


        return adminRoleManager.findRoleByIdList(roleIdList);
    }

    @Override
    public List<AdminRoleInfoDo> findAll() {
        return adminRoleManager.findAll();
    }

    @Override
    public Result<Object> addUserRole(Long userId, List<Long> roleIdList,Long loginUserId) {

        if(adminUserInfoService.findUserById(userId) == null){
            throw new AdminException(AdminExceptionEnums.ADMIN_USER_NOT_FIND_EXCEPTION.getCode());
        }

        //不能授权给比你权限高的用户
        this.checkUserRoleWeight(loginUserId,userId);

        AdminRoleInfoDo maxRoleWeight = this.getMaxRoleWeight(loginUserId);

        Integer maxWeight = maxRoleWeight.getRoleWeight();

        List<AdminUserRoleInfoDo> userRoleByUserId = adminUserRoleManager.findUserRoleByUserId(userId);

        for(Long roleId : roleIdList) {
            AdminRoleInfoDo roleById = this.findRoleById(roleId);
            if( roleById == null ){
                throw new AdminException(AdminExceptionEnums.ADMIN_ROLE_NOT_FIND_EXCEPTION.getCode());
            }
            //不能授权给高于自己的角色
            if(roleById.getRoleWeight() >= maxRoleWeight.getRoleWeight()){
                throw new AdminException(PublicExceptionCodeEnum.EX_JURISDICTION_LESS.getCode());
            }
            //如果用户与此角色没有建立绑定关系,添加到需要保存的集合里
            if(!adminUserRoleManager.checkUserRole(userId, roleId)){
                adminUserRoleManager.addUserRole(userId, roleId, loginUserId);
            }
            if(maxWeight < roleById.getRoleWeight()){
                maxWeight = roleById.getRoleWeight();
            }
        }

        //刷新用户最高权限
        if(maxWeight > maxRoleWeight.getRoleWeight()){
            adminUserInfoService.updateUserWeight(userId,loginUserId,maxWeight);
        }

        //删除掉需要移除的权限
        if(CollectionUtils.isNotEmpty(userRoleByUserId)){
            List<AdminUserRoleInfoDo> delIdList = userRoleByUserId.stream().filter(user -> !roleIdList.contains(user.getAdminRoleId())).collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(delIdList)){
                delIdList.stream().forEach(id -> adminUserRoleManager.removeUserRole(userId,id.getAdminRoleId(),loginUserId));
            }
        }

        //刷新用户和角色和权限

        return ResultFactory.success();
    }



    @Override
    public void checkUserRoleWeight(Long loginUserId, Long operationUserId) {
        AdminRoleInfoDo loginMaxWeight = this.getMaxRoleWeight(loginUserId);

        if(loginMaxWeight == null){
            throw new AdminException(PublicExceptionCodeEnum.EX_JURISDICTION_LESS.getCode());
        }else {
            AdminRoleInfoDo maxRoleWeight = this.getMaxRoleWeight(operationUserId);
            //不能删除权限比自己大的用户
            if(maxRoleWeight != null && loginMaxWeight.getRoleWeight() <= maxRoleWeight.getRoleWeight()){
                throw new AdminException(PublicExceptionCodeEnum.EX_JURISDICTION_LESS.getCode());
            }
        }
    }


    private AdminRoleInfoDo getMaxRoleWeight(Long userId) {
        List<AdminUserRoleInfoDo> userRoleByUserId = adminUserRoleManager.findUserRoleByUserId(userId);

        if(CollectionUtils.isEmpty(userRoleByUserId)){
           return null;
        }

        List<Long> roleIdList = userRoleByUserId.stream().map(role -> role.getAdminRoleId()).collect(Collectors.toList());

        List<AdminRoleInfoDo> roleByIdList = adminRoleManager.findRoleByIdList(roleIdList);

        if(CollectionUtils.isEmpty(roleByIdList)){
           return null;
        }

        return roleByIdList.stream().max(Comparator.comparingLong(role -> role.getRoleWeight())).orElse(null);
    }
}
