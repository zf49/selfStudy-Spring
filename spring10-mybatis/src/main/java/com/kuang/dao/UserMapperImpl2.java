package com.kuang.dao;

import com.kuang.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {


    @Override
    public List<User> getUserList() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper =  sqlSession.getMapper(UserMapper.class);

        List<User> userList = mapper.getUserList();
        System.out.println("SqlSessionDaoSupport");
         return userList;

    }
}
