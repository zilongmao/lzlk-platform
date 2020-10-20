package com.lzlk.job.quartz.service;


import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.utils.text.StringBuilderHolder;
import com.lzlk.job.quartz.base.AStandardBaseJob;
import com.lzlk.job.quartz.factory.SchedulerJobFactory;
import com.lzlk.job.quartz.factory.SchedulerTriggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class SchedulerJobService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private SchedulerJobFactory schedulerJobFactory;

    @Autowired
    private SchedulerTriggerFactory schedulerTriggerFactory;

    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    public void setupJobs() throws ParseException, SchedulerException {
        Map<String, AStandardBaseJob> beanMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
                AStandardBaseJob.class);
        log.info(" ====== task扫描注册已启动 共找到task数为[ {} ]个, ====== ", beanMap != null ? beanMap.size() : 0);
        if (beanMap != null && beanMap.size() > 0) {
            beanMap.forEach((key, value) -> {
                if (!this.baseCheck(value)) {
                    return;
                }
                try {
                    scheduleJob(value.getClass(), null, value.initCronValue(), StringBuilderHolder.getGlobal().
                                    append(value.initAppId()).append("-").append(value.initCronKey()).toString(),
                            value.initCronKey());
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public Date scheduleJob(Class<? extends QuartzJobBean> object, JobDataMap jMap, String frequency, String jobName,
                            String triggerName) throws ParseException, SchedulerException {
        JobDetailFactoryBean jobDetailFactoryBean = schedulerJobFactory.job(object, jMap, jobName);
        CronTriggerFactoryBean cronTriggerFactoryBean = schedulerTriggerFactory
                .jobTrigger(jobDetailFactoryBean.getObject(), frequency, triggerName);
        return schedulerFactoryBean.getScheduler().scheduleJob(jobDetailFactoryBean.getObject(),
                cronTriggerFactoryBean.getObject());
    }

    /**
     * 基本校验
     *
     * @Description
     * @author 邻座旅客
     * @param aTaskStandardJob
     * @return
     */
    public boolean baseCheck(AStandardBaseJob aTaskStandardJob) {
        if (StringUtils.isBlank(aTaskStandardJob.initAppId())) {
            log.error("【task扫描注册出现异常】-类[{}]-[appId]-不能为空", aTaskStandardJob.getClass());
            return false;
        }

        if (StringUtils.isBlank(aTaskStandardJob.initCronKey())) {
            log.error("【task扫描注册出现异常】-类[{}]-[CronKey]-不能为空", aTaskStandardJob.getClass());
            return false;
        }

        if (StringUtils.isBlank(aTaskStandardJob.initCronValue())) {
            log.error("【task扫描注册出现异常】-类[{}]-[CronValue]-不能为空", aTaskStandardJob.getClass());
            return false;
        }

        if (aTaskStandardJob.initValidSign() == BaseConstants.VALID_SIGN_Y) {
            log.info("【task扫描注册】-类[{}]-[valid_sign]-为无效", aTaskStandardJob.getClass());
            return false;
        }
        return true;
    }

}