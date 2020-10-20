package com.lzlk.payment.business.wechat.controller;

import com.lzlk.base.utils.request.RequestUtil;
import com.lzlk.payment.business.wechat.service.WeChatCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/9 01 21
 * @Author: 邻座旅客
 */
@RestController
@RequestMapping("/weChat")
@Slf4j
public class WeChatCallbackController {

    @Resource
    private WeChatCallbackService weChatCallbackService;

    @RequestMapping("/callback")
    @CrossOrigin
    public String callback(HttpServletRequest request){
        log.info("================ 接收到微信回调 ================");
        InputStream inStream;
        String result = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            // 获取微信调用我们notify_url的返回信息
            result = new String(outSteam.toByteArray(), "utf-8");

            Document document = DocumentHelper.parseText(result);
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                String name = element.getName();
                String text = element.getText();
                map.put(name, text);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(" ================ request url : {} ================ ", RequestUtil.getErrorMsg(request));
        }
        return weChatCallbackService.callback(map);
    }
}
