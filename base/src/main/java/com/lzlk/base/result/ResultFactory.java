package com.lzlk.base.result;


import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;

public class ResultFactory {

    /**
     * 成功结果
     * 
     * @Description
     * @author 邻座旅客
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return new Result<T>(PublicExceptionCodeEnum.SUCCESS.getCode(), PublicExceptionCodeEnum.SUCCESS.getMsg());
    }

    /**
     * 成功结果
     * 
     * @Description
     * @author 邻座旅客
     * @param <T>
     * @param data
     *            返回数据
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(PublicExceptionCodeEnum.SUCCESS.getCode(), PublicExceptionCodeEnum.SUCCESS.getMsg(),
                data);
    }

    /**
     * 成功结果
     * 
     * @Description
     * @author 邻座旅客
     * @param <T>
     * @param data
     *            返回数据
     * @param total
     *            数据条数
     * @return
     */
    public static <T> Result<T> success(T data, Long total) {
        return new Result<T>(PublicExceptionCodeEnum.SUCCESS.getCode(), PublicExceptionCodeEnum.SUCCESS.getMsg(),
                data, total);
    }

    /**
     * 失败结果
     * 
     * @Description
     * @author 邻座旅客
     * @param <T>
     * @param code
     *            返回代码
     * @param message
     *            返回信息
     * @return
     */
    public static <T> Result<T> failure(String code, String message) {
        return new Result<T>(code, message);
    }

    public static <T> Result<T> failure() {
        return new Result<T>(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode(),
                PublicExceptionCodeEnum.EX_OPERATION_FAIL.getMsg());
    }

    /**
     * 失败结果
     * 
     * @Description
     * @author 邻座旅客
     * @param <T>
     * @param code
     *            返回代码
     * @param message
     *            返回信息
     * @param data
     *            返回数据
     * @return
     */
    public static <T> Result<T> failure(String code, String message, T data) {
        return new Result<T>(code, message, data);
    }

}
