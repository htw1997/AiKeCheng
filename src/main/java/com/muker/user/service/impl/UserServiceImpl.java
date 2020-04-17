package com.muker.user.service.impl;

import com.muker.config.RedisConfig;
import com.muker.config.RedisKeyConfig;
import com.muker.user.dao.UserMapper;
import com.muker.user.entity.User;
import com.muker.user.service.UserService;
import com.muker.utils.JwtUtil;
import com.muker.utils.RedisUtil;
import com.muker.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author JavaQ
 * @Date 2019/12/17 19:59
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMapper userMapper;

    @Override
    public R checkUser(String phone) {
        User user = userMapper.selectByPhone(phone);
        if (user != null) {
            return R.fail("用户名已存在");
        } else {
            return R.Ok("用户名可以注册");
        }
    }

    @Override
    public R register(User user) {
        if (checkUser(user.getPhone()).getCode() == 200) {
            if (userMapper.insert(user) > 0) {
                return R.Ok("注册成功");
            } else {
                return R.fail();
            }
        } else {
            return R.fail("注册失败");
        }
    }

    @Override
    public R login(String phone, String password) {
        User user = userMapper.selectByPhone(phone);
        if (user != null && password.equals(user.getPassword())) {
            String token = JwtUtil.createJWT(user.getId().toString());
            redisUtil.setex(RedisKeyConfig.TOKEN_KEY + phone, token, RedisKeyConfig.TOKEN_TIME);

            Map<String,Object> usermap = new HashMap<>();
            usermap.put("token",token);
            usermap.put("user",user);
            System.out.println(usermap);
            return R.Ok(usermap);
        } else {
            return R.fail("用户名或密码不正确");
        }
    }

    @Override
    public R queryUserInfoByPhone(String phone) {
        return R.Ok(userMapper.selectUserInfo(phone));
    }

    @Override
    public R queryById(String token) {
        return R.Ok(userMapper.selectById(Integer.parseInt(JwtUtil.parseJWT(token))));
    }
}
