package com.muker.Personal.service;

import com.muker.Personal.dto.PhoneCodeDto;
import com.muker.Personal.entity.UserInfos;
import com.muker.user.entity.User;
import com.muker.vo.R;


public interface PersonalService {


    /**
     * 展示用户信息
     * @return
     */
    R showUserInfo( String phone);

//-----------------------------------------账户绑定模块-------------------------------------------------

// --------------------------手机-----------------------

    /**
     * 验证手机验证码
     * @param phoneCodeDto
     * @return
     */
    R checkSmsCode(PhoneCodeDto phoneCodeDto);

    /**
     * 绑定手机号
     * @param phoneCodeDto  用户信息
     * @return
     */
    R bindPhone(PhoneCodeDto phoneCodeDto);

    /**
     * 向用户发送验证码
     * @param rusername  用户注册的账号
     * @return
     */
    R sendSmsCode(String rusername);

    /**
     * 取消绑定手机号
     * @param userInfos 用户
     * @return
     */
    R cancelBindPhone(UserInfos userInfos);


    // --------------------------邮箱-----------------------

    /**
     * 绑定邮箱
     * @param email  用户
     * @return
     */
    int bindEmail(String email);

    /**
     * 发送邮箱验证码
     * @param email  用户输入的邮箱
     * @return
     */
    R sendEmailCode(String email);

    /**
     * 查找邮箱信息是否存在
     * @param email 用户输入的邮箱
     * @return
     */
    UserInfos selectEmail(String email);




}
