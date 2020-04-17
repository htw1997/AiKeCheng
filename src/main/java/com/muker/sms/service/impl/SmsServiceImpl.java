package com.muker.sms.service.impl;

import com.muker.config.RedisKeyConfig;
import com.muker.sms.dto.PhoneCodeDto;
import com.muker.sms.service.SmsService;
import com.muker.utils.AliSmsUtil;
import com.muker.utils.DateUtil;
import com.muker.utils.RandomUtil;
import com.muker.utils.RedisUtil;
import com.muker.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SmsServiceImpl
 * @Description
 * @Author JavaQ
 * @Date 2019/12/11 16:42
 **/
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public R sendSmsCode(String phone) {
        int code;

        // 验证码的有效期 第二次发送 判断验证码是否过期  没过期的问题解决
        if (redisUtil.exists(RedisKeyConfig.SMS_CODE + phone)) {
            //验证码未失效
            //1、更改有效期 重新设置为10分钟
            code = Integer.parseInt(redisUtil.get(RedisKeyConfig.SMS_CODE + phone, 0));
            redisUtil.expire(RedisKeyConfig.SMS_CODE + phone, RedisKeyConfig.CODE_TIME, 0);
        } else {
            code = RandomUtil.createNum(4);
        }

        AliSmsUtil.sendSmsCode(code, phone);
        redisUtil.setex(RedisKeyConfig.SMS_CODE + phone, Integer.toString(code), RedisKeyConfig.CODE_TIME);
        redisUtil.incr("sendMinuteCount:" + phone);
        redisUtil.setExpire("sendMinuteCount:" + phone, 60);
        redisUtil.setex("sendHourCount:" + phone + System.currentTimeMillis(), 60 * 60, "1");
        redisUtil.incr("sendDayCount:" + phone);
        if ("1".equals(redisUtil.get("sendDayCount:" + phone, 0))) {
            redisUtil.setExpire("sendDayCount:" + phone, DateUtil.getSecondToZero());
        }

        return R.Ok();
    }

    @Override
    public R checkSmsCode(PhoneCodeDto phoneCodeDto) {
        if (redisUtil.exists(RedisKeyConfig.SMS_CODE + phoneCodeDto.getPhone())) {
            if (Integer.parseInt(redisUtil.get(RedisKeyConfig.SMS_CODE + phoneCodeDto.getPhone(), 0)) == (phoneCodeDto.getCode())) {
                return R.Ok();
            }
        }
        return R.fail("校验失败");
    }


}
