package com.lzlk.mysql.manager.system.impl;

import com.lzlk.base.enums.math.NumberEnums;
import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDo;
import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDoExample;
import com.lzlk.dao.mybatis.system.mapper.SystemParameterConfigMapper;
import com.lzlk.mysql.manager.system.SystemParameterManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 21 48
 * @Author: 邻座旅客
 */
@Service
public class SystemParameterManagerImpl implements SystemParameterManager {

    @Resource
    private SystemParameterConfigMapper systemParameterMapper;

    @Override
    public SystemParameterConfigDo getParamByKey(String key) {

        SystemParameterConfigDoExample example = new SystemParameterConfigDoExample();
        SystemParameterConfigDoExample.Criteria criteria = example.createCriteria();
        criteria.andParameterKeyEqualTo(key)
                .andIsDeleteEqualTo(false);
        List<SystemParameterConfigDo> systemParameterDos = systemParameterMapper.selectByExample(example);
        return CollectionUtils.isEmpty(systemParameterDos) ? null : systemParameterDos.get(NumberEnums.ZERO.getNumber());
    }

    @Override
    public boolean update(SystemParameterConfigDo systemParameterDo) {
        systemParameterDo.setLastModifyTime(new Date());
        return systemParameterMapper.updateByPrimaryKey(systemParameterDo) > 0;
    }

    /**
     * 修改
     *
     * @param key
     * @param value
     *
     * @return
     */
    @Override
    public boolean updateParamByKey(String key, String value) {
        SystemParameterConfigDoExample example = new SystemParameterConfigDoExample();
        SystemParameterConfigDoExample.Criteria criteria = example.createCriteria();
        criteria.andParameterKeyEqualTo(key)
                .andIsDeleteEqualTo(false);
        SystemParameterConfigDo systemParameterDo = new SystemParameterConfigDo();
        systemParameterDo.setLastModifyAdminId(0L);
        systemParameterDo.setLastModifyTime(new Date());
        systemParameterDo.setParameterValue(value);
        return  systemParameterMapper.updateByExampleSelective(systemParameterDo,example) > 0;
    }

    @Override
    public SystemParameterConfigDo getParamById(Long id) {
        SystemParameterConfigDoExample example = new SystemParameterConfigDoExample();
        SystemParameterConfigDoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id)
                .andIsDeleteEqualTo(false);
        List<SystemParameterConfigDo> systemParameterDos = systemParameterMapper.selectByExample(example);
        return CollectionUtils.isEmpty(systemParameterDos) ? null : systemParameterDos.get(NumberEnums.ZERO.getNumber());
    }

    @Override
    public List<SystemParameterConfigDo> findChargeConfig() {
        SystemParameterConfigDoExample example = new SystemParameterConfigDoExample();
        SystemParameterConfigDoExample.Criteria criteria = example.createCriteria();
        criteria.andParameterKeyLike("%config%");
        return systemParameterMapper.selectByExample(example);
    }

    @Override
    public SystemParameterConfigDo selectParamInfoById(Long id) {
        return systemParameterMapper.selectByPrimaryKey(id);
    }
}
