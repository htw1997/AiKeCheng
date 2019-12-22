//package com.muker.service.impl;
//
//import com.muker.dao.PersonalDao;
//import com.muker.dao.UserDao;
//import com.muker.entity.Users;
//import com.muker.entity.UsersInfos;
//import com.muker.service.PersonalService;
//import com.muker.vo.R;
//import org.apache.catalina.User;
//import org.apache.shiro.SecurityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PersonalServiceImpl implements PersonalService {
//    @Autowired
//
//    UserDao userDao;
//
//    PersonalDao personalDao;
//
//    @Override
//    public R showUserInfo() {
//        String phone = (String) SecurityUtils.getSubject().getSession().getAttribute("phone");
//        UsersInfos usersInfos = personalDao.selectUserByPhone(phone);
//        return R.Ok(usersInfos);
//    }
//}
