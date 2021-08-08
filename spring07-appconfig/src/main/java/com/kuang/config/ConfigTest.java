package com.kuang.config;


import com.kuang.config2.Config2;
import com.kuang.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration    //这个类也会被spring托管，并且注入到容器中，因为它本身就是一个Component
//@Configuration代表一个配置类就和我们之前.XML是一样的
@ComponentScan("com.kuang") //扫描包
@Import(Config2.class)  //原来在xml中引入地址，现在饮用class文件
public class ConfigTest {

    //注册一个bean,就相当于我们之前写的bean标签
    //方法的名字就是bean标签的id
    //返回值就是bean标签中的class
    @Bean
      public User user(){
          return new User();  //就是返回要注册到bean中的对象
      }

}
