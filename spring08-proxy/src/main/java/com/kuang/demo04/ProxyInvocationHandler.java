package com.kuang.demo04;


import com.kuang.demo03.Rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//我们会用户这个类来生成代理类
public class ProxyInvocationHandler implements InvocationHandler {
    
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
         log(method.getName());
        
        Object result = method.invoke(target, args);
        return result;
    }

      public void log(String msg){
          System.out.println("执行了"+msg+"方法");
      }

}
