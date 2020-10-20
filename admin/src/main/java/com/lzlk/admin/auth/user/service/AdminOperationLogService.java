package com.lzlk.admin.auth.user.service;


import com.lzlk.base.result.Result;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/7/5 19:55
 * @Created by 湖南达联
 */
public interface AdminOperationLogService {

    /**
     * 查询后台操作日志
     * @param startTime
     * @param endTime
     * @param adminUserId
     * @param pageNo
     * @return
     */
    Result<Object> findLogByPage(Long startTime, Long endTime, Long adminUserId, Integer pageNo);
}
