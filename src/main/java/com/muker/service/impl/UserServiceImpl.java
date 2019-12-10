package com.muker.service.impl;

import com.muker.dao.UserDao;
import com.muker.entity.Users;
import com.muker.service.UserService;
import com.muker.vo.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;



    @Override
    public R login(String phone, String password) {

        //查找phone是否在数据库中存在
        Users count = userDao.selectByPhone(phone);
        System.out.println(count);
        if (count != null){
            //验证密码和电话是否正确
            if (count.getPassword().equals(password)&&count.getPhone().equals(phone)){

                //1.操作shiro，创建主题对象
                Subject subject = SecurityUtils.getSubject();
                //2.创建令牌  用户名密码令牌
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(count.getPhone(),count.getPassword());
                //3、存储当前的User
                subject.getSession().setAttribute("phone",phone);
                //4、登录  告诉Shiro登录成功
                subject.login(usernamePasswordToken);

                return R.Ok();
            }
        }
        return R.fail("用户名或密码错误");
    }
}
