package com.muker.Personal.dao;


import com.muker.Personal.entity.UserInfos;
import com.muker.vo.R;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDao {


    /**
     *  根据手机查找用户信息
     * @param phone
     * @return
     */
    UserInfos selectUserByPhone(String phone);

    /**
     * 绑定邮箱
     * @param email  用户
     * @return
     */
    int bindEmail(String email);

    /**
     *  查找邮箱
     * @param email
     * @return
     */
    UserInfos selectEmail(String email);

    /**
     * 根据email来查找用户信息
     * @param email
     * @return
     */
    UserInfos selectShowUserByEmail(String email);

    /**
     * 注册邮箱
     * @param email  邮箱
     * @param emailCode   邮箱验证码
     * @return
     */
    int insertEmailCode(String email, String emailCode);

    /**
     * 绑定手机号
     * @param phone
     * @return
     */


    /**
     * 取消绑定手机号
     * @param userInfos
     * @return
     */
    R cancelBindPhone(UserInfos userInfos);

    R bindPhone(String rusername);
}
