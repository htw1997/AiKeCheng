package com.muker.user.web;

import com.auth0.jwt.JWT;
import com.muker.user.entity.User;
import com.muker.user.service.UserService;
import com.muker.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description
 * @Author JavaQ
 * @Date 2019/12/17 19:07
 **/
@Api(value = "实现用户模块的操作", tags = "实现用户模块的操作")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "实现用户登录", notes = "实现用户登录")
    @GetMapping("/aimu/user/login.do")
    public R login(String phone,String password) {
        return userService.login(phone, password);
    }

    @ApiOperation(value = "实现用户名的校验", notes = "实现用户名的校验")
    @GetMapping("/aimu/user/check.do")
    public R check(String phone) {
        return userService.checkUser(phone);
    }

    @ApiOperation(value = "根据Id查询用户", notes = "根据Id查询用户")
    @GetMapping("/aimu/user/querybyid.do")
    @CrossOrigin
    public R queryById(HttpServletRequest request) {
        String token = request.getHeader("aimutoken");
        System.out.println(token);
        return userService.queryById(token);
    }

    @ApiOperation(value = "实现用户名的注册", notes = "实现用户名的注册")
    @PostMapping("/aimu/user/register.do")
    @CrossOrigin
    public R register(User user) {
        return userService.register(user);
    }

    @ApiOperation(value = "实现用户信息的查询", notes = "实现用户信息的查询")
    @PostMapping("/aimu/user/searchinfo.do")
    public R searchinfo(String phone) {
        return userService.queryUserInfoByPhone(phone);
    }
}
