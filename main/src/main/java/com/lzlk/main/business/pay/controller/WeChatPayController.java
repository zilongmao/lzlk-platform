package com.lzlk.main.business.pay.controller;

import com.lzlk.base.annotation.auth.AuthPassport;
import com.lzlk.base.annotation.auth.CurrentUser;
import com.lzlk.base.enums.pay.PayChannelEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.utils.request.RequestUtil;
import com.lzlk.dao.mybatis.user.bean.UserInfoDo;
import com.lzlk.main.business.pay.service.WeChatPayService;
import com.lzlk.main.business.pay.vo.WeChatPayVo;
import com.lzlk.main.exception.MainException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/9 11 45
 * @Author: 邻座旅客
 */
@Slf4j
@Api(tags = "微信支付相关")
@RestController
@RequestMapping("/{source}/pay")
public class WeChatPayController {

    @Value("${server.auth.ip}")
    private String serverAuthIp;

    @Resource
    private WeChatPayService weChatPayService;

    @AuthPassport
    @ApiOperation("微信支付")
    @PostMapping("/payByWeChatApplets")
    @CrossOrigin
    public Result<Object> payByWeChatApplets(@CurrentUser @Ignore UserInfoDo userInfoDo,
                                             @RequestBody WeChatPayVo weChatPayVo, HttpServletRequest request) {
        return weChatPayService.payByWeChatApplets(userInfoDo.getId(), weChatPayVo, request);
    }

    @GetMapping("/respondPayNotice")
    @ApiIgnore
    @ApiOperation("警告：此接口用于服务之间的通信,前端不允许调用")
    public Result<Object> respondPayNotice(HttpServletRequest request, @RequestParam Integer payChannel, @RequestParam String localTradeNo) {

        // 校验来源ip
        if (!serverAuthIp.contains(RequestUtil.getIPAddress(request))) {
            log.error(serverAuthIp + " =============== 发现非法调用 {} =============== ", RequestUtil.getIPAddress(request));
            throw new MainException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }

        if (PayChannelEnums.eval(payChannel) == null) {
            log.error(" =============== 发现非法调用 {} =============== ", payChannel);
            throw new MainException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }
        return weChatPayService.respondPayNotice(payChannel, localTradeNo);
    }
}
