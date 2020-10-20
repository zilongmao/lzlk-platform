package com.lzlk.admin.auth.role.service.impl;



import com.github.pagehelper.PageInfo;
import com.lzlk.admin.auth.role.service.AdminPermissionService;
import com.lzlk.admin.auth.role.vo.*;
import com.lzlk.admin.auth.user.vo.PageVo;
import com.lzlk.admin.common.annotation.constants.AdminConstants;
import com.lzlk.admin.common.util.AdminUtil;
import com.lzlk.admin.exception.AdminException;
import com.lzlk.admin.exception.AdminExceptionEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.base.utils.spring.SpringCglibBeanUtils;
import com.lzlk.dao.mybatis.admin.bean.AdminPermissionInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRolePermissionInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import com.lzlk.mysql.manager.admin.AdminPermissionManager;
import com.lzlk.mysql.manager.admin.AdminRoleManager;
import com.lzlk.mysql.manager.admin.AdminRolePermissionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/21 15:38
 * @Created by 湖南达联
 */
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Resource
    private AdminRoleManager adminRoleManager;

    @Resource
    private AdminPermissionManager adminPermissionManager;

    @Resource
    private AdminRolePermissionManager adminRolePermissionManager;

    @Resource
    private ApplicationContext applicationContext;


    @Override
    public Result<Object> findPermissionByPage(Integer pageNo, Long pid) {
        if(pid == null){
            pid = AdminConstants.MEUN_ROOT;
        }

        PageInfo<AdminPermissionInfoDo> permissionByPage = adminPermissionManager.findPermissionByPage(pid, pageNo, true);
        PageVo pageVo = new PageVo();
        pageVo.setDataList(SpringCglibBeanUtils.convertByList(permissionByPage.getList(), PermissionVo.class));
        pageVo.setTotal(permissionByPage.getTotal());
        return ResultFactory.success(pageVo);
    }

    @Override
    public Result<Object> addPermission(AddPermissionVo vo, Long loginUserId) {
        AdminPermissionInfoDo permissionInfoDo = SpringCglibBeanUtils.convert(vo,AdminPermissionInfoDo.class);
        permissionInfoDo.setCreateUserId(loginUserId);
        permissionInfoDo.setUpdateUserId(loginUserId);
        Integer permissionSortNum = adminPermissionManager.getPermissionSortNum(vo.getPid());
        permissionInfoDo.setSortNum(permissionSortNum);
        adminPermissionManager.addPermission(permissionInfoDo);

        return ResultFactory.success();
    }

    @Override
    public Result<Object> findLoginUserMenu(Long loginUserId) {
        return ResultFactory.success(this.getLoginUserMenu(loginUserId));
    }

    @Override
    public List<MenuVo> getLoginUserMenu(Long loginUserId) {
        Set<String> roleSet = AdminUtil.getLoginUserRole();

        if(CollectionUtils.isEmpty(roleSet)){
            return new ArrayList<>();
        }

        List<Long> roleIdList = roleSet.stream().map(roleId -> Long.valueOf(roleId)).collect(Collectors.toList());

        List<AdminRolePermissionInfoDo> rolePermissionByRoleIdList = adminRolePermissionManager.findRolePermissionByRoleIdList(roleIdList);


        if(CollectionUtils.isEmpty(rolePermissionByRoleIdList)){
            return new ArrayList<>();
        }

        List<Long> idList = rolePermissionByRoleIdList.stream()
                .map(id -> Long.valueOf(id.getAdminPermissionId())).collect(Collectors.toList());
        //查询所有菜单
        List<AdminPermissionInfoDo> permissionByIdList = adminPermissionManager.findPermissionByIdList(idList,null);

        Map<Long, List<AdminPermissionInfoDo>> map  = permissionByIdList.stream().collect(Collectors.groupingBy(info -> info.getPid()));
        //获取一级的菜单
        List<AdminPermissionInfoDo> rootMenu = map.get(AdminConstants.MEUN_ROOT);


        if(CollectionUtils.isEmpty(rootMenu)){
            return new ArrayList<>();
        }
        //排序
        Collections.sort(rootMenu,Comparator.comparing(AdminPermissionInfoDo :: getSortNum));

        return rootMenu.stream().map(menu -> {
            PermissionVo convert = SpringCglibBeanUtils.convert(menu, PermissionVo.class);
            MenuVo menuVo = new MenuVo();
            menuVo.setRootMenu(convert);
            List<AdminPermissionInfoDo> adminPermissionInfoDos = map.get(convert.getId());
            if (CollectionUtils.isNotEmpty(adminPermissionInfoDos)) {
                //子菜单也需要排序
                List<PermissionVo> nextMenu = SpringCglibBeanUtils.convertByList(adminPermissionInfoDos, PermissionVo.class);
                Collections.sort(nextMenu,Comparator.comparing(PermissionVo :: getSortNum));
                menuVo.setNextMenu(nextMenu);
            }
            return menuVo;
        }).collect(Collectors.toList());

    }

    @Override
    public Result<Object> addRolePermission(RolePermissionVo vo, Long loginUserId) {

        AdminRoleInfoDo roleById = adminRoleManager.findRoleById(vo.getRoleId());

        if(roleById == null){
              throw new AdminException(AdminExceptionEnums.ADMIN_ROLE_NOT_FIND_EXCEPTION.getCode());
        }

        List<AdminRolePermissionInfoDo> rolePermissionByRoleId = adminRolePermissionManager.findRolePermissionByRoleId(roleById.getId());

        for(Long id : vo.getPermissionIdList()){

            if(CollectionUtils.isNotEmpty(rolePermissionByRoleId) && adminRolePermissionManager.checkRolePermission(vo.getRoleId(),id)){
                 continue;
            }
            //保存添加的
            adminRolePermissionManager.addRolePermission(vo.getRoleId(),id,loginUserId);
        }

        if(CollectionUtils.isEmpty(rolePermissionByRoleId)){
             return ResultFactory.success();
        }


        List<Long> oleIdList = rolePermissionByRoleId.stream().map(id -> id.getAdminPermissionId()).collect(Collectors.toList());

        //获取需要删除的id
        List<Long> delList = oleIdList.stream().filter(id -> !vo.getPermissionIdList().contains(id)).collect(Collectors.toList());

        //如果不为空,删除掉
        if(CollectionUtils.isNotEmpty(delList)){
            delList.stream().forEach( id -> adminRolePermissionManager.removeRolePermission(vo.getRoleId(),id,loginUserId));
        }

        return ResultFactory.success();
    }

    @Override
    public Result<Object> updatePermission(UpdatePermissionVo vo) {
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();

        if(!adminPermissionManager.checkPermission(vo.getId())){
            throw new AdminException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
        }

        AdminPermissionInfoDo adminPermissionInfoDo = SpringCglibBeanUtils.convert(vo, AdminPermissionInfoDo.class);
        adminPermissionInfoDo.setUpdateTime(new Date());
        adminPermissionInfoDo.setUpdateUserId(loginUser.getId());
        adminPermissionInfoDo.setSortNum(vo.getSortNum() == null ? null :vo.getSortNum());
        adminPermissionManager.updatePermission(adminPermissionInfoDo);
        return ResultFactory.success();
    }


    @Override
    public List<String> findAllRequestMappingUrl(){
        RequestMappingHandlerMapping mapping  = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        handlerMethods.keySet().forEach(info -> {
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            patterns.forEach(url -> urlList.add(url));
        });
        return urlList;
    }

    @Override
    public Result<Object> findPermissionUrL() {

        List<String> allRequestMappingUrl = this.findAllRequestMappingUrl();

        if(CollectionUtils.isEmpty(allRequestMappingUrl)){
            return ResultFactory.success();
        }

        Set<String> urlSet = new HashSet<>();

        for (String url : allRequestMappingUrl){
            urlSet.add(url.substring(0,url.lastIndexOf("/")));
        }

        return ResultFactory.success(urlSet);
    }

    @Override
    public Result<Object> findRolePermissionByRoleId(Long roleId) {
        List<AdminRolePermissionInfoDo> rolePermissionByRoleId = adminRolePermissionManager.findRolePermissionByRoleId(roleId);
        if(CollectionUtils.isEmpty(rolePermissionByRoleId)){
            return ResultFactory.success();
        }
        List<Long> collect = rolePermissionByRoleId.stream().map(info -> info.getAdminPermissionId()).collect(Collectors.toList());
        return ResultFactory.success(collect);
    }

    public static void main(String[] args) {
        String url = "/admin/role/find";
        System.out.println(url.lastIndexOf("/"));
        System.out.println(url.substring(0,url.lastIndexOf("/")));
        String[] split = url.split("/");
        System.out.println("======================");
        for(int i = 0 ; i < split.length; i++){
            System.out.println(split[i]);
        }
        System.out.println("======================");
    }

}
