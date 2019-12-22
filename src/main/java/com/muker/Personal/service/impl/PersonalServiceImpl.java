package com.muker.Personal.service.impl;




import com.muker.Personal.entity.UserInfos;
import com.muker.config.RedisKeyConfig;
import com.muker.Personal.dao.PersonalDao;
import com.muker.Personal.dto.PhoneCodeDto;
import com.muker.Personal.service.PersonalService;
import com.muker.user.entity.User;
import com.muker.utils.*;
import com.muker.vo.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    PersonalDao personalDao;

    JedisUtil jedisUtil;

    @Override
    public R showUserInfo(String phone ) {
        UserInfos userInfos = personalDao.selectUserByPhone(phone);
        return R.Ok(userInfos);
    }

    @Override
    public UserInfos selectEmail(String email) {
        return personalDao.selectEmail(email);
    }



    @Override
    public int bindEmail(String email) {
        return personalDao.bindEmail(email);
    }


    @Override
    public R cancelBindPhone(UserInfos userInfos) {
        return personalDao.cancelBindPhone(userInfos);
    }

    @Override
    public R sendEmailCode(String email) {


        //获取六位随机验证码
        String validateCode = MailUtils.getValidateCode(6);
        System.out.println(validateCode);
        UserInfos userInfos = new UserInfos();
        userInfos.setEmail(email);
        userInfos.setEmailCode(validateCode);

        MailUtils.sendMail(userInfos.getEmail(), "尊敬的读者，您好，您正在注册慕课网会员，您的验证码是：" + validateCode +
                ",请及时输入验证码，欢迎进入慕课大家庭！", "慕课网");

        UserInfos userInfos1 = personalDao.selectShowUserByEmail(userInfos.getEmail());
        System.out.println("userInfos1" + userInfos1);
        //验证是否存在该账号
        if (userInfos1 == null) {
            //若不存在
            int count = personalDao.insertEmailCode(userInfos.getEmail(), userInfos.getEmailCode());
            if (count > 0){
                return R.Ok("注册成功");
            }
        } else {
            return R.fail("注册失败");
        }

        return R.fail("该账号已存在");
    }

    @Override
    public R sendSmsCode(String rusername) {
        int code;
        //1、校验之前的验证码有没有失效
        if (jedisUtil.checkKey(RedisKeyConfig.SMS_CODE + rusername)) {
            //验证码未失效
            //1、更改有效期 重新设置为10分钟
            code = Integer.parseInt(jedisUtil.getStr(RedisKeyConfig.SMS_CODE + rusername));
            jedisUtil.setExpire(RedisKeyConfig.SMS_CODE + rusername, RedisKeyConfig.CODE_TIME);
            //2、检测有效期剩余超过1半 不改变直接返回 小于一半 重新更新有效期
            //3、重新生成 把原来覆盖

        } else {
            //生成验证码
            code = RandomUtil.createNum(4);
        }
        //2、发送短信
        AliSmsUntil.AliSmsUtil.sendSmsCode(rusername, code);
        //3、验证码记录到Redis
        jedisUtil.addStr(RedisKeyConfig.SMS_CODE + rusername, code + "", RedisKeyConfig.CODE_TIME);
        return R.Ok();
    }




    @Override
    public R checkSmsCode(PhoneCodeDto codeDto) {
        //1、校验验证码有效性
        if (jedisUtil.checkKey(RedisKeyConfig.SMS_CODE + codeDto.getRusername())) {
            //2、校验是否正确
            //jedisUtil.getStr 查询   将RedisKeyConfig.SMS_CODE + phoneCodeDto.getPhone() 获取的值转换为key值
            if (codeDto.getCode() == Integer.parseInt(jedisUtil.getStr(RedisKeyConfig.SMS_CODE + codeDto.getRusername()))) {
                return R.Ok();
            }
        }
        return R.fail("校验失败");
    }

    @Override
    public R bindPhone(PhoneCodeDto phoneCodeDto) {

        return personalDao.bindPhone(phoneCodeDto.getRusername());

    }


}
