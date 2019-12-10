package com.muker.entity;

import lombok.Data;

@Data
public class Users {

    private Integer id;

    private String phone;

    private String password;
    //权限
    private String perms;
}
