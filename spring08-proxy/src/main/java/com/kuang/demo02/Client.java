package com.kuang.demo02;

public class Client {


    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserProxy userProxy = new UserProxy(userService);

        userProxy.add();

    }

}
