package com.lzlk.base.plugin.snowflakeid.service;

public interface ISnowflakeIdService {

    /**
     * 下一个全局ID
     * @return
     */
    long nextId();


    /**
     * 下一个全局ID-普通资讯 1:订单id
     * @return
     */
    long nextOrderId(Long dataId);



}
