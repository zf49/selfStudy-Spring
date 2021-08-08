package com.kuang.pojo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//等价于    <bean id="user" class="com.kuang.pojo.User"/>
@Component
@Scope("singleton")
public class User {

//     @Value("Value annotation")
     public String name;

     @Value("Value annotation")
     //     相当于   <bean id="user" class="com.kuang.pojo.User">
//        <property name="name" value="Value annotation"/>
//    </bean>
     public void setName(String name) {

          this.name = name;
     }
}
