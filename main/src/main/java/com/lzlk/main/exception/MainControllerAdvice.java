package com.lzlk.main.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.lzlk.base.exception.BaseException;
import com.lzlk.base.exception.ExceptionLog;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.base.utils.request.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description 全局异常捕捉类
 * @Date 2020年9月20日 00:03:25
 * @Created by 湖南爱豆
 */
@RestController
@ControllerAdvice
@Slf4j
public class MainControllerAdvice {

    private final static String EXCEPTION_MSG_KEY = "Exception message : ";

    /**
     * 业务异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public Result<Object> handleException(BaseException ex, HttpServletRequest request) {
        ExceptionLog.errorPrintStraceAndPath(log, RequestUtil.getRequestPath(request), ex);
        return ResultFactory.failure(ex.getErrorCode(), ex.getMsg());
    }


    /**
     * 未知异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception ex, HttpServletRequest request) {
        MainException MainException = new MainException(PublicExceptionCodeEnum.EX_UNKNOWN.getCode(), ex);
        ExceptionLog.errorPrintStraceAndPath(log, RequestUtil.getErrorMsg(request), MainException);
        log.error("Exception ex:{}",ex.getMessage());
        return ResultFactory.failure(PublicExceptionCodeEnum.EX_UNKNOWN.getCode(), PublicExceptionCodeEnum.EX_UNKNOWN.getMsg());
    }

    /**
     * 空指针异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<Object> handleException(NullPointerException ex, HttpServletRequest request) {
        MainException MainException = new MainException(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), MainException);
        //把异常打到日志
        log.error("NullPointerException ex:{}", ExceptionUtils.getStackTrace(ex));

        return ResultFactory.failure(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode(), PublicExceptionCodeEnum.EX_OPERATION_FAIL.getMsg());
    }

    /**
     * 文件上传超出大小限制
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handleException(MaxUploadSizeExceededException ex, HttpServletRequest request) {
        MainException MainException = new MainException(PublicExceptionCodeEnum.Ex_FILE_SIZE_MAX_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), MainException);
        return ResultFactory.failure(PublicExceptionCodeEnum.Ex_FILE_SIZE_MAX_ERROR.getCode(), PublicExceptionCodeEnum.Ex_FILE_SIZE_MAX_ERROR.getMsg(), ex.getMessage());
    }

    /**
     * 方法参数类型不匹配
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        PublicExceptionCodeEnum exParamTypeError = PublicExceptionCodeEnum.EX_PARAM_TYPE_ERROR;
        MainException MainException = new MainException(exParamTypeError.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), MainException);
        return ResultFactory.failure(exParamTypeError.getCode(), exParamTypeError.getMsg(), ex.getLocalizedMessage());
    }

    /**
     * 丢失路径变量
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({MissingPathVariableException.class})
    public Result missingPathVariableException(MissingPathVariableException ex, HttpServletRequest request){
        PublicExceptionCodeEnum paramError = PublicExceptionCodeEnum.Ex_PARAM_ERROR;
        MainException MainException = new MainException(paramError.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), MainException);
        return ResultFactory.failure(paramError.getCode(), paramError.getMsg(), ex.getVariableName());
    }

    /**
     * Http请求方法不支持
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Object> handleException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        MainException weChatSysException = new MainException(PublicExceptionCodeEnum.EX_METHOD_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), weChatSysException);
        //
        return ResultFactory.failure(PublicExceptionCodeEnum.EX_METHOD_ERROR.getCode(), PublicExceptionCodeEnum.EX_METHOD_ERROR.getMsg());
    }

    /**
     * 参数不合法
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        MainException MainException = new MainException
                (PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), MainException);

        BindingResult a = ex.getBindingResult();
        List<ObjectError> list = a.getAllErrors();
        String errorMsg = PublicExceptionCodeEnum.Ex_PARAM_ERROR.getMsg();
        if (!CollectionUtils.isEmpty(list)) {
            errorMsg = list.get(0).getDefaultMessage();
        }
        return ResultFactory.failure(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), errorMsg);
    }

    /**
     * 参数异常
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Object> handleException(IllegalArgumentException ex, HttpServletRequest request) {
        MainException MainException = new MainException
                (PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), MainException);
        return ResultFactory.failure(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), PublicExceptionCodeEnum.Ex_PARAM_ERROR.getMsg());
    }

    /**
     * 缺少Servlet请求参数
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Object> missingServletRequestParameterException(MissingServletRequestParameterException ex,
                                                                  HttpServletRequest request) {
        MainException MainException = new MainException
                (PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), MainException);
        return ResultFactory.failure(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(),
                PublicExceptionCodeEnum.Ex_PARAM_ERROR.getMsg(), ex.getParameterName());
    }

    /**
     * Http消息不可读
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Object> httpMessageNotReadableException(UnrecognizedPropertyException ex,
                                                          HttpServletRequest request) {
        MainException MainException = new MainException
                (PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), MainException);
        return ResultFactory.failure(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(),
                PublicExceptionCodeEnum.Ex_PARAM_ERROR.getMsg(), ex.getPropertyName());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true) {
            @Override
            public void setAsText(String text) {
                try {
                    super.setAsText(text);
                } catch (Exception ex) {
                    ExceptionLog.error(log, new MainException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex));
                }
            }
        });
    }


}
