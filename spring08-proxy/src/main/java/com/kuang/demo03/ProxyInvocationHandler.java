package com.kuang.demo03;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//我们会用户这个类来生成代理类
public class ProxyInvocationHandler implements InvocationHandler {


    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),rent.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       seeHouse();
        Object result = method.invoke(rent, args);
        getFee();
        return result;
    }


    public void seeHouse(){
        System.out.println("中介看房");
    }

    public void getFee(){
        System.out.println("收中介费");
    }



}
