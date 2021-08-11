package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> getUserList();

    int addUser(User user);

    int deleteUser(@Param("id") int id);

}
