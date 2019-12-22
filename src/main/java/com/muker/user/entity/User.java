package com.muker.user.entity;


import lombok.Data;

/**
 * @ClassName User
 * @Description
 * @Author JavaQ
 * @Date 2019/12/17 19:21
 **/
@Data
public class User {
    private Integer id;

    private String phone;

    private String password;
    //权限
    private String perms;

}
