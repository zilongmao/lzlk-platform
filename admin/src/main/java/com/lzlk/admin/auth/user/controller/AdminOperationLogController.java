package com.lzlk.admin.auth.user.controller;

import com.lzlk.admin.auth.user.service.AdminOperationLogService;
import com.lzlk.admin.common.annotation.AdminLog;
import com.lzlk.admin.common.annotation.AuthPower;
import com.lzlk.base.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/7/5 19:55
 * @Created by 湖南达联
 */
@RestController
@RequestMapping("/admin/log")
@Api(tags = "后台操作日志")
public class AdminOperationLogController {

    @Resource
    private AdminOperationLogService adminOperationLogService;


    @GetMapping("/findLogByPage")
    @ApiOperation("查询后台操作记录")
    @AuthPower
    @AdminLog
    public Result<Object> findLogByPage(Long startTime, Long endTime, Long adminUserId, Integer pageNo){
        return adminOperationLogService.findLogByPage(startTime, endTime, adminUserId, pageNo);
    }
}
