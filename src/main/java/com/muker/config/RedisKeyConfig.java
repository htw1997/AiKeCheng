package com.muker.config;

import lombok.Data;

/**
 * @ClassName RedisKeyConfig
 * @Description
 * @Author JavaQ
 * @Date 2019/12/17 20:00
 **/
@Data
public class RedisKeyConfig {
    public static final String TOKEN_KEY = "aimutoken:";
    public static final int TOKEN_TIME = 1800;

    //记录短信验证码
    public static final String SMS_CODE="sms:code:";
    public static final int CODE_TIME=600; //十分钟
}
