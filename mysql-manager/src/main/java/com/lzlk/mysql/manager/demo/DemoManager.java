package com.lzlk.mysql.manager.demo;


import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDo;

import java.util.List;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/8/26 11 12
 * @Author: 邻座旅客
 */
public interface DemoManager {

    List<SystemParameterConfigDo> findDemo();
}
