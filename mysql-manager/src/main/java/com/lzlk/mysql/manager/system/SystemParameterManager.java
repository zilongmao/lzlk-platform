package com.lzlk.mysql.manager.system;


import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDo;

import java.util.List;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 21 45
 * @Author: 邻座旅客
 */
public interface SystemParameterManager {

    /**
     * 根据key找到系统参数配置
     * @param key
     * @return
     */
    SystemParameterConfigDo getParamByKey(String key);

    /**
     * 修改系统参数配置
     * @param systemParameterDo
     * @return
     */
    boolean update(SystemParameterConfigDo systemParameterDo);

    /**
     * 修改
     * @param key
     * @param value
     * @return
     */
    boolean updateParamByKey(String key, String value);

    /**
     * 根据id查找系统参数配置
     * @param id
     * @return
     */
    SystemParameterConfigDo getParamById(Long id);

    /**
     * 查询所有收费配置信息
     * @return
     */
    List<SystemParameterConfigDo> findChargeConfig();

    /**
     * 查询配置信息（包括是删除状态）
     * @param id 主键
     * @return
     */
    SystemParameterConfigDo selectParamInfoById(Long id);
}
