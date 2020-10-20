package com.lzlk.main.interceptor;
import com.lzlk.base.annotation.auth.AuthPassport;
import com.lzlk.base.annotation.auth.CurrentUser;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.redis.user.UserInfoKey;
import com.lzlk.dao.mybatis.user.bean.UserInfoDo;
import com.lzlk.main.exception.MainException;
import com.lzlk.mysql.manager.user.UserInfoManager;
import com.lzlk.nosql.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;


/**
 * @Classname 邻座旅客
 * @Description 增加方法注入, 将含有 @CurrentUser 注解的方法参数注入当前登录用户
 * @Date 2019/3/14 16:50
 * @Created by 湖南达联
 */
@Slf4j
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    private UserInfoManager userInfoManager;

    @Resource
    private RedisService redisService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        AuthPassport methodAnnotation = parameter.getMethodAnnotation(AuthPassport.class);

        Long userId = (Long) webRequest.getAttribute(BaseConstants.REQUEST_LOGIN_USER_ID_KET, RequestAttributes.SCOPE_REQUEST);

        if (methodAnnotation != null && !methodAnnotation.validate() && userId == null) {
            return null;
        }

        if (userId == null) {
            //
            throw new MainException(PublicExceptionCodeEnum.EX_USER_NOT_LOGIN_ERROR.getCode());
        }

        String userKey = UserInfoKey.getUserInfoCacheKey(userId);

        UserInfoDo user = redisService.get(userKey);
        if (user != null) {
            return user;
        }

        user = userInfoManager.queryById(userId);
        if (user == null) {
            throw new MainException(PublicExceptionCodeEnum.EX_USER_NOT_LOGIN_ERROR.getCode());
        }
        redisService.set(userKey, user, UserInfoKey.OUT_TIME);
        return user;
    }

}
