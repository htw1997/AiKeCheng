package com.muker.Personal.entity;

import lombok.Data;

@Data
public class UserInfos {

    private Integer id;
    //注册序号
    private Integer rid;
    //注册账号
    private String rusername;
    //昵称
    private String nickname;
    //职位
    private String position;
    //城市
    private String city;

    private Integer sexs;
    //个性签名
    private String idiograph;
    //关注标签
    private String label;
    //头像
    private String headimage;

    private String email;
    //邮箱验证码
    private String emailCode;
}
