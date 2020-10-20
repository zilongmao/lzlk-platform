package com.lzlk.job.business.demo.service.impl;

import com.lzlk.job.business.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/27 17 58
 * @Author: 邻座旅客
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public void demoJob() {
        // 在这里写业务代码
        log.info("执行任务中~~~");
    }
}
