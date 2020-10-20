package com.lzlk.mysql.manager.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.dao.mybatis.admin.bean.AdminRequestLogDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRequestLogDoExample;
import com.lzlk.dao.mybatis.admin.mapper.AdminRequestLogMapper;
import com.lzlk.mysql.manager.admin.AdminRequestLogManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/24 20:54
 * @Created by 湖南达联
 */
@Service
public class AdminRequestLogManagerImpl implements AdminRequestLogManager {

    @Resource
    private AdminRequestLogMapper adminRequestLogMapper;


    @Override
    public void addLog(AdminRequestLogDo log) {
        log.setCreateTime(new Date());
        if (StringUtils.isBlank(log.getArgs())){
            log.setArgs(JSONObject.toJSONString(new ArrayList<>()));
        }
        adminRequestLogMapper.insertSelective(log);
    }

    @Override
    public List<AdminRequestLogDo> findAdminLogByPage(Long startTime, Long endTime, Long adminUserId, Integer pageNo) {
        AdminRequestLogDoExample example = new AdminRequestLogDoExample();
        AdminRequestLogDoExample.Criteria criteria = example.createCriteria();
        if(startTime != null && endTime != null){
            criteria.andCreateTimeBetween(new Date(startTime),new Date(endTime));
        }
        if(adminUserId != null){
            criteria.andAdminUserIdEqualTo(adminUserId);
        }
        example.setOrderByClause( " id desc");
        PageHelper.startPage(pageNo, BaseConstants.DEFAULT_PAGE_LIMIT);
        List<AdminRequestLogDo> adminRequestLogDos = adminRequestLogMapper.selectByExample(example);
        PageInfo<AdminRequestLogDo> pageInfo = new PageInfo<>(adminRequestLogDos);
        return pageInfo == null ? null : pageInfo.getList();
    }
}
