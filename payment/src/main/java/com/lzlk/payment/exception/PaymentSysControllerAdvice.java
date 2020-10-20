package com.lzlk.payment.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.lzlk.base.exception.BaseException;
import com.lzlk.base.exception.ExceptionLog;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.base.utils.request.RequestUtil;
import lombok.extern.slf4j.Slf4j;
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
import java.util.regex.Pattern;

@RestController
@ControllerAdvice
@Slf4j
public class PaymentSysControllerAdvice {

    private final static  Pattern URI = Pattern.compile("/sign/sendMobileVerifyCode/");

    /**
     * 业务异常
     *
     * @param ex
     * @return
     * @Description
     * @author 邻座旅客
     */
    @ExceptionHandler(BaseException.class)
    public Result<Object> handleException(BaseException ex, HttpServletRequest request) {
        ExceptionLog.errorPrintStraceAndPath(log, RequestUtil.getRequestPath(request), ex);
        return ResultFactory.failure(ex.getErrorCode(), ex.getMsg());
    }



    /**
     * 未知异常
     *
     * @param ex
     * @return
     * @Description
     * @author 邻座旅客
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception ex, HttpServletRequest request) {
        PaymentException weChatSysException = new PaymentException(PublicExceptionCodeEnum.EX_UNKNOWN.getCode(), ex);
        ExceptionLog.errorPrintStraceAndPath(log, RequestUtil.getErrorMsg(request), weChatSysException);
        log.error(ex.getMessage());
        return ResultFactory.failure(PublicExceptionCodeEnum.EX_UNKNOWN.getCode(), PublicExceptionCodeEnum.EX_UNKNOWN.getMsg());
    }

    /**
     * 空指针异常
     *
     * @param ex
     * @return
     * @Description
     * @author 邻座旅客
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<Object> handleException(NullPointerException ex, HttpServletRequest request) {
        PaymentException weChatSysException = new PaymentException(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), weChatSysException);
        ex.printStackTrace();
        return ResultFactory.failure(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode(), PublicExceptionCodeEnum.EX_OPERATION_FAIL.getMsg());
    }

    /**
     * Maximum upload size exceeded : the file size > file config[tomcat] size
     *
     * @param ex      MaxUploadSizeExceededException
     * @param request
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handleException(MaxUploadSizeExceededException ex, HttpServletRequest request) {
        PaymentException PaymentException = new PaymentException(PublicExceptionCodeEnum.Ex_FILE_SIZE_MAX_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), PaymentException);
        return ResultFactory.failure(PublicExceptionCodeEnum.Ex_FILE_SIZE_MAX_ERROR.getCode(), PublicExceptionCodeEnum.Ex_FILE_SIZE_MAX_ERROR.getMsg(), ex.getMessage());
    }

    /**
     * request argument type exception！
     * exp：the arg type is int,but req arg type is string <br/>
     * Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        PublicExceptionCodeEnum exParamTypeError = PublicExceptionCodeEnum.EX_PARAM_TYPE_ERROR;
        PaymentException PaymentException = new PaymentException(exParamTypeError.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), PaymentException);
        return ResultFactory.failure(exParamTypeError.getCode(), exParamTypeError.getMsg(), ex.getLocalizedMessage());
    }

    /**
     * pathVariable not be null ,but the request uri not find the pathVariable
     * exp: the uri is "?name=张三/{arg}",but request is "?name=张三"  <br/>
     * Missing URI template variable 'version' for method parameter of type String
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({MissingPathVariableException.class})
    public Result missingPathVariableException(MissingPathVariableException ex, HttpServletRequest request){
        PublicExceptionCodeEnum paramError = PublicExceptionCodeEnum.Ex_PARAM_ERROR;
        PaymentException PaymentException = new PaymentException(paramError.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), PaymentException);
        return ResultFactory.failure(paramError.getCode(), paramError.getMsg(), ex.getVariableName());
    }

    /**
     * http请求method有误异常
     *
     * @param ex
     * @return
     * @Description
     * @author 邻座旅客
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Object> handleException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        PaymentException weChatSysException = new PaymentException(PublicExceptionCodeEnum.EX_METHOD_ERROR.getCode(), ex);
        //如果是被刷接口的我们就不打印他
        //indexof 复杂度O(m*n) 正则时间复杂度O(n)
        boolean flag =  URI.matcher(request.getRequestURI()).find() && "GET".equalsIgnoreCase(request.getMethod());
        if (flag){
            return null;
        }
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), weChatSysException);
        //
        return ResultFactory.failure(PublicExceptionCodeEnum.EX_METHOD_ERROR.getCode(), PublicExceptionCodeEnum.EX_METHOD_ERROR.getMsg());
    }

    /**
     * 参数不合法异常
     *
     * @param ex
     * @return
     * @Description
     * @author 邻座旅客
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        PaymentException PaymentException = new PaymentException
                (PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), PaymentException);

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
     *
     * @param ex
     * @return
     * @Description
     * @author 邻座旅客
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Object> handleException(IllegalArgumentException ex, HttpServletRequest request) {
        PaymentException PaymentException = new PaymentException
                (PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), PaymentException);
        return ResultFactory.failure(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), PublicExceptionCodeEnum.Ex_PARAM_ERROR.getMsg());
    }

    /**
     * 参数异常
     *
     * @param ex
     * @return
     * @Description
     * @author 邻座旅客
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Object> missingServletRequestParameterException(MissingServletRequestParameterException ex,
                                                                  HttpServletRequest request) {
        PaymentException PaymentException = new PaymentException
                (PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), PaymentException);
        return ResultFactory.failure(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(),
                PublicExceptionCodeEnum.Ex_PARAM_ERROR.getMsg(), ex.getParameterName());
    }

    /**
     * 参数异常
     *
     * @param ex
     * @return
     * @Description
     * @author 邻座旅客
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Object> httpMessageNotReadableException(UnrecognizedPropertyException ex,
                                                          HttpServletRequest request) {
        PaymentException PaymentException = new PaymentException
                (PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex);
        ExceptionLog.errorAndPath(log, RequestUtil.getErrorMsg(request), PaymentException);
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
                    ExceptionLog.error(log, new PaymentException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode(), ex));
                }
            }
        });
    }


}
