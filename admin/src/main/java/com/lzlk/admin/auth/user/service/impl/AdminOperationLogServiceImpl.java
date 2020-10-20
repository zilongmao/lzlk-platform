package com.lzlk.admin.auth.user.service.impl;



import com.lzlk.admin.auth.user.service.AdminOperationLogService;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.mysql.manager.admin.AdminRequestLogManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/7/5 19:57
 * @Created by 湖南达联
 */
@Service
public class AdminOperationLogServiceImpl implements AdminOperationLogService {

    @Resource
    private AdminRequestLogManager adminRequestLogManager;

    @Override
    public Result<Object> findLogByPage(Long startTime, Long endTime, Long adminUserId, Integer pageNo) {
        if(startTime == null && endTime != null){
            startTime = 0L;
        }
        if(startTime != null && endTime == null){
            endTime = System.currentTimeMillis();
        }
        return ResultFactory.success(adminRequestLogManager.findAdminLogByPage(startTime, endTime, adminUserId, pageNo));
    }
}
