package com.lzlk.job.business.demo.job;

import com.lzlk.dao.mybatis.system.bean.SystemJobConfigDo;
import com.lzlk.job.business.demo.service.DemoService;
import com.lzlk.job.constants.DemoJobConstants;
import com.lzlk.job.quartz.base.AStandardBaseJob;
import com.lzlk.mysql.manager.system.SystemJobConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/16 15 51
 * @Author: 邻座旅客
 */
@Slf4j
@Component
public class DemoJob extends AStandardBaseJob {

    @Resource
    private DemoService demoService;

    @Resource
    private SystemJobConfigManager systemJobConfigManager;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public String initAppId() {
        return DemoJobConstants.APP_ID;
    }

    @Override
    public String initCronKey() {
        return applicationContext.getEnvironment().getProperty("demo.job.demo.key");
    }

    @Override
    public String initCronValue() {
        return applicationContext.getEnvironment().getProperty("demo.job.demo.cron");
    }

    @Override
    public int initValidSign() {
        String sign = applicationContext.getEnvironment().getProperty("demo.job.demo.sign");
        return NumberUtils.toInt(sign);
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String jobName = DemoJob.class.getSimpleName();
        try {
            SystemJobConfigDo systemJobConfigDo = systemJobConfigManager.findByJobName(jobName);
            if (systemJobConfigDo == null) {
                log.error("======================= 未定义经的job,请先配置: {} =======================", jobName);
                return;
            }
            log.info("======================= 任务 {} 开始执行 =======================", jobName);
            if (systemJobConfigDo.getStatus().equals(DemoJobConstants.JOB_STATUS_WAIT)) {
                systemJobConfigManager.updateJobStatus(jobName, DemoJobConstants.JOB_STATUS_RUN);
                demoService.demoJob();
            }
        } catch (Exception e) {
            log.error("======================= 任务 {} 执行失败 =======================", jobName);
            e.printStackTrace();
        } finally {
            systemJobConfigManager.updateJobStatus(jobName, DemoJobConstants.JOB_STATUS_WAIT);
        }
        log.info("======================= 任务 {} 执行完毕 =======================", jobName);
    }

}
