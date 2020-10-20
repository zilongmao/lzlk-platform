package com.lzlk.main.business.service.config.impl;

import com.lzlk.base.enums.redis.RedisKeyEnums;
import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDo;
import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDoExample;
import com.lzlk.dao.mybatis.system.mapper.SystemParameterConfigMapper;
import com.lzlk.main.business.service.config.SystemConfigService;
import com.lzlk.nosql.redis.service.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/8/17 10 44
 * @Author: 邻座旅客
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    @Resource
    private RedisService redisService;

    @Resource
    private SystemParameterConfigMapper systemParameterConfigMapper;

    @Override
    public String getStrConfigByKey(String key) {
        String config = redisService.get(RedisKeyEnums.SYSTEM_CONFIG_CACHE_KEY.getCacheKey(key));
        if (StringUtils.isEmpty(config)) {
            SystemParameterConfigDoExample example = new SystemParameterConfigDoExample();
            SystemParameterConfigDoExample.Criteria criteria = example.createCriteria();
            criteria.andParameterKeyEqualTo(key);
            config = systemParameterConfigMapper.selectByExample(example).get(0).getParameterValue();
            redisService.set(RedisKeyEnums.SYSTEM_CONFIG_CACHE_KEY.getCacheKey(key), config, RedisKeyEnums.SYSTEM_CONFIG_CACHE_KEY.getExpireTime());
        }
        return config;
    }

    @Override
    public Integer getIntegerConfigByKey(String key) {
        Integer config = redisService.get(RedisKeyEnums.SYSTEM_CONFIG_CACHE_KEY.getCacheKey(key));
        if (config == null) {
            SystemParameterConfigDoExample example = new SystemParameterConfigDoExample();
            SystemParameterConfigDoExample.Criteria criteria = example.createCriteria();
            criteria.andParameterKeyEqualTo(key);
            List<SystemParameterConfigDo> systemParameterConfigDos = systemParameterConfigMapper.selectByExample(example);
            config = Integer.valueOf(systemParameterConfigDos.get(0).getParameterValue());
            redisService.set(RedisKeyEnums.SYSTEM_CONFIG_CACHE_KEY.getCacheKey(key), config, RedisKeyEnums.SYSTEM_CONFIG_CACHE_KEY.getExpireTime());
        }
        return config;
    }
}
