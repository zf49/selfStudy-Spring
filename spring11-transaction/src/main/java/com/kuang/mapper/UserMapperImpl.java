package com.kuang.mapper;

import com.kuang.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper{

    //我们的所有操作 都使用sqlSession
//    现在使用sqlSessionTemplate
    private SqlSessionTemplate sqlSession;

//


    public UserMapperImpl(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> getUserList() {

        User user = new User(10,"小王","123123");
        
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.addUser(user);
         mapper.deleteUser(9);
        List<User> userList = mapper.getUserList();
        return userList;
    }

    @Override
    public int addUser(User user) {

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.addUser(user);
        System.out.println("添加成功");
        return i;

    }

    @Override
    public int deleteUser(int id) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.deleteUser(id);
        System.out.println("删除成功");
        return i;


    }


}
