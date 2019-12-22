package com.muker.user.service;

import com.muker.user.entity.User;
import com.muker.vo.R;

public interface UserService {

    R checkUser(String phone);
    R register(User user);
    R login(String phone,String password);
    R queryUserInfoByPhone(String phone);
    R queryById(String token);
}
