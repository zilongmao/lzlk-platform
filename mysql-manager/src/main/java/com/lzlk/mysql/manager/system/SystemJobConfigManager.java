package com.lzlk.mysql.manager.system;


import com.lzlk.dao.mybatis.system.bean.SystemJobConfigDo;

/**
 * TODO: 定时任务Manager
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 21 45
 * @Author: 邻座旅客
 */
public interface SystemJobConfigManager {


    /**
     * 根据jobName 查询job配置
     * @param jobName
     * @return
     */
    SystemJobConfigDo findByJobName(String jobName);


    /**
     * 修改任务执行状态
     * @param jobName
     * @param jobStatus
     * @return
     */
    boolean updateJobStatus(String jobName, Integer jobStatus);
}
