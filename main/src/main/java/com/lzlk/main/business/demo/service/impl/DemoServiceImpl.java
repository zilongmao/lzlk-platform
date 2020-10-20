package com.lzlk.main.business.demo.service.impl;

import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDo;
import com.lzlk.main.business.demo.service.DemoService;
import com.lzlk.mysql.manager.demo.DemoManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/7/22 15 58
 * @Author: 邻座旅客
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoManager demoManager;

    @Override
    public Result<Object> findDemo() {

        List<SystemParameterConfigDo> demo = demoManager.findDemo();
        return ResultFactory.success(demo);
    }

    public static void main(String[] args) {
        System.out.println(1);
    }
}
