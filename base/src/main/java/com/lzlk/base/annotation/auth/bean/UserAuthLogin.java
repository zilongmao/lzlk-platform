package com.lzlk.base.annotation.auth.bean;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * TODO: jwt json对象
 *
 * @Created by 湖南爱豆
 * @Date 2020/7/22 15 07
 * @Author: 邻座旅客
 */
@Data
@Getter
@Setter
public class UserAuthLogin implements Serializable {


    private Long loginUserId;//用户id

    private Long timeOut;//过期时间

}
