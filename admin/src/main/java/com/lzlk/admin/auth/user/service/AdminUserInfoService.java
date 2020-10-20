package com.lzlk.admin.auth.user.service;


import com.lzlk.admin.auth.user.vo.AddUserInfoVo;
import com.lzlk.admin.auth.user.vo.AdminUpdatePassWordVo;
import com.lzlk.base.result.Result;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/18 16:59
 * @Created by 湖南达联
 */
public interface AdminUserInfoService {


    /**
     * 修改用户登陆时间和登陆ip地址
     * @param loginName
     * @param ip
     * @return
     */
    Result<Object> loginSuccess(String loginName, String ip);

    /**
     * 根据loginName 获取用户
     * @param loginName
     * @return
     */
    AdminUserInfoDo findUserByLoginName(String loginName);

    /**
     * 查询id查询用户
     * @param userId
     * @return
     */
    AdminUserInfoDo findUserById(Long userId);

    /**
     * 新增用户
     * @param vo
     * @param loginUserId
     * @return
     */
    Result<Object> addAdminUserInfo(AddUserInfoVo vo, Long loginUserId);

    /**
     * 删除用户
     * @param loginName
     * @param loginUserId
     * @param isDelete
     * @return
     */
    Result<Object> updateAdminUserInfoState(String loginName, Long loginUserId, Integer isDelete);

    /**
     * 修改密码
     * @param vo
     * @return
     */
    Result<Object> updatePassWord(AdminUpdatePassWordVo vo);

    /**
     * 重置密码
     * @param loginName
     * @param loginUserId
     * @return
     */
    Result<Object> refreshUserPassWord(String loginName, Long loginUserId);

    /**
     * 查询用户信息
     * @param userId
     * @param loginName
     * @param pageNo
     * @param isDelete
     * @return
     */
    Result<Object> findUserInfoPage(Long userId, String loginName, Integer pageNo, Integer isDelete);

    /**
     * 检查用户登陆名称是存在
     * @param loginName
     * @return
     */
    Result<Object> checkLoginName(String loginName);

    /**
     * 修改用户权限
     * @param userId
     * @param loginUserId
     * @param weight
     */
    void updateUserWeight(Long userId, Long loginUserId, Integer weight);




}
