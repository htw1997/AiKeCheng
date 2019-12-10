package com.muker.controller;

import com.muker.service.UserService;
import com.muker.entity.Users;
import com.muker.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "关于用户的相关接口",tags = "实现我的界面用户相关功能")
public class UserController {

    @Autowired
    private  UserService userService;
    @PostMapping("/api/user/login.do")
    @ApiOperation(value = "登录",notes = "输入手机号和密码登录")
    public R login(String phone,String password){
        return userService.login(phone,password);
    }

    @PostMapping("/api/user/register.do")
    @ApiOperation(value = "注册用户", notes = "进行用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号", name = "phone", required = true, dataType = "String"),
            @ApiImplicitParam(value = "密码", name = "password", required = true, dataType = "String")
    })
    public R register(Users user) {
        return null;
    }
}
