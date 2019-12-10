package com.muker.dao;

import com.muker.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserDao {

    Users selectByPhone(String phone);


}
