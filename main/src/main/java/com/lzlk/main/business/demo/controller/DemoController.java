package com.lzlk.main.business.demo.controller;

import com.lzlk.base.result.Result;
import com.lzlk.main.business.demo.service.DemoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/7/22 15 58
 * @Author: 邻座旅客
 */
@Api(tags = "这是一个例子")
@RestController
@RequestMapping("/{source}/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/findDemo")
    public Result<Object> findDemo(){
        return demoService.findDemo();
    }
}
