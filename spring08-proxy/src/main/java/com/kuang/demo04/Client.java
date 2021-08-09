package com.kuang.demo04;

import com.kuang.demo02.UserService;
import com.kuang.demo02.UserServiceImpl;

import java.lang.reflect.InvocationHandler;

public class Client {
    public static void main(String[] args) {
//        真实角色
        UserServiceImpl userService = new UserServiceImpl();

        ProxyInvocationHandler pih = new ProxyInvocationHandler();

        pih.setTarget(userService);

        UserService proxy = (UserService) pih.getProxy();
        
         proxy.query();

    }
}
