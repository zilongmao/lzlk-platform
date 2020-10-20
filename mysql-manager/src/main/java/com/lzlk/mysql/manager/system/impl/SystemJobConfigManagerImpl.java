package com.lzlk.mysql.manager.system.impl;


import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.dao.mybatis.system.bean.SystemJobConfigDo;
import com.lzlk.dao.mybatis.system.bean.SystemJobConfigDoExample;
import com.lzlk.dao.mybatis.system.mapper.SystemJobConfigMapper;
import com.lzlk.mysql.manager.system.SystemJobConfigManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/03/27 19:51
 * @Created by 湖南达联
 */
@Service
public class SystemJobConfigManagerImpl implements SystemJobConfigManager {

    @Resource
    private SystemJobConfigMapper systemJobConfigMapper;

    @Override
    public SystemJobConfigDo findByJobName(String jobName) {
        SystemJobConfigDoExample example = new SystemJobConfigDoExample();
        SystemJobConfigDoExample.Criteria criteria = example.createCriteria();
        criteria.andJobNameEqualTo(jobName);
        criteria.andIsDeleteEqualTo(false);
        List<SystemJobConfigDo> systemJobConfigDos = systemJobConfigMapper.selectByExample(example);
        return CollectionUtils.isEmpty(systemJobConfigDos) ? null : systemJobConfigDos.get(0);
    }

    @Override
    public boolean updateJobStatus(String jobName, Integer jobStatus) {
        SystemJobConfigDoExample example = new SystemJobConfigDoExample();
        SystemJobConfigDoExample.Criteria criteria = example.createCriteria();
        criteria.andJobNameEqualTo(jobName);
        criteria.andIsDeleteEqualTo(false);
        SystemJobConfigDo systemJobInfoDo = new SystemJobConfigDo();
        systemJobInfoDo.setStatus(jobStatus);
        return systemJobConfigMapper.updateByExampleSelective(systemJobInfoDo, example) > 0;
    }
}
