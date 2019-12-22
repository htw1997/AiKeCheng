package com.muker.user.dao;


import com.muker.entity.UsersInfos;
import com.muker.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    // 添加一个用户
    int insert(User user);

    // 根据用户名查询用户
    User selectByPhone(String phone);

    @Select("select * from users where id = #{id}")
    @ResultType(User.class)
    User selectById(@Param("id") int id);

    UsersInfos selectUserInfo(String phone);

}
