package com.lzlk.mysql.manager.demo.impl;

import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDo;
import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDoExample;
import com.lzlk.dao.mybatis.system.mapper.SystemParameterConfigMapper;
import com.lzlk.mysql.manager.demo.DemoManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/8/26 11 13
 * @Author: 邻座旅客
 */
@Service
public class DemoManagerImpl implements DemoManager {

    // 引入DAO层
    @Resource
    private SystemParameterConfigMapper systemParameterConfigMapper;

    @Override
    public List<SystemParameterConfigDo> findDemo() {
        // 创建example对象
        SystemParameterConfigDoExample example = new SystemParameterConfigDoExample();
        SystemParameterConfigDoExample.Criteria criteria = example.createCriteria();
        // 设置查询条件 -> Code不为空
        criteria.andCreateAdminIdIsNull();
        // 返回查询条件
        return systemParameterConfigMapper.selectByExample(example);
    }
}
