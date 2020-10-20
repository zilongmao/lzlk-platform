package com.lzlk.base.plugin.snowflakeid.service.impl;


import com.lzlk.base.plugin.snowflakeid.SnowflakeIdFactory;
import com.lzlk.base.plugin.snowflakeid.service.ISnowflakeIdService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SnowflakeIdServiceImpl implements ISnowflakeIdService {

    // 普通资讯-全局ID产生对象
    private static volatile SnowflakeIdFactory snowflakeId = null;

    private static volatile SnowflakeIdFactory orderSnowflakeId = null;

    @Value("${workerId}")
    private String workerId;

    @Value("${datacenterId}")
    private String datacenterId;

    public static final ISnowflakeIdService INSTANCE = new SnowflakeIdServiceImpl();




    private SnowflakeIdServiceImpl(){

    }


    @Override
    public long nextId() {
        if (snowflakeId == null) {
            synchronized (this) {
                if(snowflakeId == null){
                    snowflakeId= new SnowflakeIdFactory(Long.valueOf(workerId),
                            Long.valueOf(datacenterId));
                }
            }
        }
        return snowflakeId.nextId();
    }



    @Override
    public long nextOrderId(Long dataId) {
        if (orderSnowflakeId == null) {
            synchronized (this) {
                if(orderSnowflakeId == null){

                    orderSnowflakeId = new SnowflakeIdFactory(Long.valueOf(workerId),
                            dataId);
                }
            }
        }
        return orderSnowflakeId.nextId();
    }


}
