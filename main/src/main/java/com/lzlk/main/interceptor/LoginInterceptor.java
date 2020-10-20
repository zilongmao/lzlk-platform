package com.lzlk.main.interceptor;

import com.lzlk.base.annotation.auth.core.WebTokenCore;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.enums.redis.ViewKey;
import com.lzlk.base.utils.http.HttpServletResponseUtil;
import com.lzlk.base.utils.request.RequestUtil;
import com.lzlk.nosql.redis.service.RedisService;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @Classname 邻座旅客
 * @Description
 * @Date 2019/3/14 12:02
 * @Created by 湖南达联
 * @Author 111
 */
@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisService redisService;

    private final static Pattern URI = Pattern.compile("/sign/sendMobileVerifyCode/");

    /**
     *
     */
    private static final DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /***
     *
     */
    private static final DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH");

    View view = new View();

    /**
     * 前置检查
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //初始化定义
        HttpServletResponseUtil.initHttpResponse(response);
        WebTokenCore webTokenCore = new WebTokenCore(request, response, handler);


      //  boolean flag = !(URI.matcher(request.getRequestURI()).find() && "GET".equalsIgnoreCase(request.getMethod()));


        return webTokenCore.jwtTokenAuthPassport();
    }


    @Async
    public void asyncLogin(HttpServletRequest request) {
        boolean flag = !(URI.matcher(request.getRequestURI()).find() && "GET".equalsIgnoreCase(request.getMethod()));
        if (flag) {
            //有个短信接口不做统计
            view.setPv();

            //对iv进行计数
            view.setIv(RequestUtil.getClientIP(request));
            //uv
            view.setUv((Long) request.getAttribute(BaseConstants.REQUEST_LOGIN_USER_ID_KET));
            //dv
            view.setDv(request);
        }
    }


    /**
     * view 方法
     * 请求实现，建议用负载或者监控实现
     */
    @AllArgsConstructor
    class View {
        /**
         * 组合pv计数
         */

        private void setPv() {
            //小时pv
            setHourPv();
            //日pv
            setDayPv();
        }

        /**
         * 设置uv
         *
         * @param userUniId
         */
        private void setUv(Long userUniId) {
            if (null == userUniId) {
                return;
            }
            //小时uv
            setHourUv(userUniId);
            //日uv
            setDayUv(userUniId);
        }

        /**
         * 设置iv
         *
         * @param ip
         */
        private void setIv(String ip) {
            setHourIv(ip);
            setDayIv(ip);
        }

        /**
         * 對dv统计
         */
        private void setDv(HttpServletRequest request) {
            setHourDv(request);
            setDayDv(request);
        }


        /**
         * 对pv计数
         */
        void setHourPv() {
            //小时统计
            ViewKey hourPv = ViewKey.HOUR_PV;
            String cacheKey = hourPv.getCacheKey(hourFormatter.format(LocalDateTime.now()));
            //8 天后失效
            redisService.opsForValInc(cacheKey, hourPv.getExpireTime());
        }

        /**
         * 对日pv计数
         */
        void setDayPv() {
            //小时统计
            ViewKey dayPv = ViewKey.DAY_PV;
            String cacheKey = dayPv.getCacheKey(dayFormatter.format(LocalDateTime.now()));
            redisService.opsForValInc(cacheKey, dayPv.getExpireTime());
        }

        /**
         * 对小时uv进行统计
         */
        void setHourUv(Long userUniId) {
            ViewKey hourUv = ViewKey.HOUR_UV;
            String cacheKey = hourUv.getCacheKey(hourFormatter.format(LocalDateTime.now()));
            Set<Long> users = new HashSet<>(1);
            users.add(userUniId);
            redisService.opsForSetAdd(cacheKey, hourUv.getExpireTime(), users);
        }

        /**
         * 对日uv进行统计
         *
         * @param userUniId
         */
        void setDayUv(Long userUniId) {
            ViewKey dayPv = ViewKey.DAY_UV;
            String cacheKey = dayPv.getCacheKey(dayFormatter.format(LocalDateTime.now()));
            Set<Long> users = new HashSet<>(1);
            users.add(userUniId);
            redisService.opsForSetAdd(cacheKey, dayPv.getExpireTime(), users);
        }

        /**
         * 设置iv
         *
         * @param ip
         */

        void setHourIv(String ip) {
            ViewKey hourIv = ViewKey.HOUR_IV;
            String cacheKey = hourIv.getCacheKey(hourFormatter.format(LocalDateTime.now()));
            Set<String> ips = new HashSet<>(1);
            ips.add(ip);
            redisService.opsForSetAdd(cacheKey, hourIv.getExpireTime(), ips);
        }

        /**
         * 设置iv
         *
         * @param ip
         */

        void setDayIv(String ip) {
            ViewKey dayIv = ViewKey.DAY_IV;
            String cacheKey = dayIv.getCacheKey(dayFormatter.format(LocalDateTime.now()));
            Set<String> ips = new HashSet<>(1);
            ips.add(ip);
            redisService.opsForSetAdd(cacheKey, dayIv.getExpireTime(), ips);
        }


        /**
         * 小时dv 计数处理
         *
         * @param request
         */

        void setHourDv(HttpServletRequest request) {
            ViewKey hourDv = ViewKey.HOUR_DV;
            OperatingSystem operatingSystem = UserAgent.parseUserAgentString(request.getHeader("user-agent")).getOperatingSystem().getGroup();
            dv(hourDv, operatingSystem);
        }

        /**
         * 访问设备进行统计
         *
         * @param request
         */

        void setDayDv(HttpServletRequest request) {
            ViewKey hourDv = ViewKey.DAY_DV;
            OperatingSystem operatingSystem = UserAgent.parseUserAgentString(request.getHeader("user-agent")).getOperatingSystem().getGroup();
            dv(hourDv, operatingSystem);
        }

        /**
         * 对设备进行统计
         *
         * @param viewKey
         * @param operatingSystem
         */
        void dv(ViewKey viewKey, OperatingSystem operatingSystem) {
            String date = null;
            if (ViewKey.DAY_DV.equals(viewKey)) {
                date = dayFormatter.format(LocalDateTime.now());
            }
            if (ViewKey.HOUR_DV.equals(viewKey)) {
                date = hourFormatter.format(LocalDateTime.now());
            }

            if (null == date) {
                return;
            }

            if (OperatingSystem.IOS.equals(operatingSystem)) {
                redisService.opsForValInc(viewKey.getCacheKey(date, OperatingSystem.IOS.getName()), viewKey.getExpireTime());
            } else if (OperatingSystem.ANDROID.equals(operatingSystem)) {
                redisService.opsForValInc(viewKey.getCacheKey(date, OperatingSystem.ANDROID.getName()), viewKey.getExpireTime());
            } else {
                redisService.opsForValInc(viewKey.getCacheKey(date, OperatingSystem.UNKNOWN.getName()), viewKey.getExpireTime());
            }
        }


    }




}
