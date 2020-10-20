package com.lzlk.base.result;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 *  
 *
 * 【标题】: 数据返回vo
 * 【描述】: 
 * 【版权】: 湖南达联
 * 【作者】: 邻座旅客
 * 【时间】: 2017年6月21日 下午2:34:00
 * </pre>
 * 
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    /** @Fields serialVersionUID: */

    private static final long serialVersionUID = -3389116110462499678L;

    /**
     * 返回代码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;


    public Result() {

    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(String code, String message, T data, Long total) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    @Override
    public String toString() {
        return "ResultVo [code=" + code + ", message=" + message + ", data=" + data + "]";
    }

}
