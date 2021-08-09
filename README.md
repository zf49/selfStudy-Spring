## 常用依赖
```xml
<?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd">
   <context:component-scan base-package="x x x x x.xxxxx.xxxx"/>
       <context:annotation-config/>
   
   </beans>
```

## 使用注解开发

![image-20210809002846550](/Users/chris/Library/Application Support/typora-user-images/image-20210809002846550.png)

![image-20210809002809360](/Users/chris/Library/Application Support/typora-user-images/image-20210809002809360.png)

```xml
<context:component-scan base-package="com.kuang.pojo"/>
```

2. 属性如何注入

```java
@Component
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
```

3. 衍生注解

@Component 有几个衍生注解，我们在web开发中，会按照mvc三层架构来分层

- dao [@Repository]

- service [@Service]

  - controller [@Controller]

  这四个注解功能都是一样的, 都是代表某个类注册到Spring中，装配Bean!!!

4. ## 自动装配

   

   ```xml
   - @Autowired: 自动装配通过类型，名字
     如果Autowired的自动装配环境比较复杂，则使用@Qualifier(value="xxx")来实现
   - @Nullable: 字段标记了这个注解，则该字段可以为null
   - @Resource: 先通过名字，后通过类型来自动装配
   ```

   

5. 作用域Scope

   ![image-20210809005124822](/Users/chris/Library/Application Support/typora-user-images/image-20210809005124822.png)

```xm
@Scope("singleton")
@Scope("protoType")
```

![image-20210809005047905](/Users/chris/Library/Application Support/typora-user-images/image-20210809005047905.png)

6. 小结

   ![image-20210809005339223](/Users/chris/Library/Application Support/typora-user-images/image-20210809005339223.png)

## 注解说明

- @Autowired: 自动装配通过类型，名字
  如果Autowired的自动装配环境比较复杂，则使用@Qualifier(value="xxx")来实现

- @Nullable: 字段标记了这个注解，则该字段可以为null

- @Resource: 先通过名字，后通过类型来自动装配

  -------------------------------

- @Component: 组件，放在类上说明这个类被Spring管理了，也就是bean

- dao [@Repository]

- service [@Service]

- controller [@Controller]

  这四个注解功能都是一样的, 都是代表某个类注册到Spring中，装配Bean!!!

------------------------

## 使用Java的方式配置Spring

实体类：

```java
//这个注解就是说明，这个类被spring接管了，注册到了容器中
@Component
public class User {
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Value("使用注解去开发") //属性注入值
    public void setName(String name) {
        this.name = name;
    }
}
```

配置类：

```java
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
```

```java
@Configuration
@ComponentScan("com.kuang")
public class Config2 {

}
```

测试类

```java
public class Mytest {

    @Test
    public void test1() {

        //如果完全树勇配置类来做，我们就只能通过AnnotationConfig上下文来获取容器
        //通过配置类的class文件来对对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigTest.class);
        User user = (User) context.getBean("user");

        System.out.println(user);
    }


}
```

## 10. 代理模式

springAOP的底层就是代理模式

代理模式的分类

- 静态代理
- 动态代理

![image-20210809195957970](/Users/chris/JAVA/Self-study/spring-study/img/WX20210809-195908@2x.png)



### 静态代理

角色分析：

- 抽象角色：一把是同借口或者抽象类
- 真是角色： 被代理的角色
- 代理角色： 代理真实角色，代理真实角色后，我们一般会做一些附加操作
- 客户：访问代理对象的人

代码步骤

1. 接口
2. 真实角色
3. 代理角色
4. 客户端访问

代理模式的好处

- 可以是真实角色的操作更加存粹，不用关注一些公共服务
- 公共服务交给代理角色！实现了业务的分工
- 公共服务发生拓展是，方便集中管理

缺点

- 一个真实角色就会禅师一个代理角色，代码量会翻倍，开发效率会变低

![image-20210809201354152](/Users/chris/Library/Application Support/typora-user-images/image-20210809201354152.png)

![image-20210809201415614](/Users/chris/Library/Application Support/typora-user-images/image-20210809201415614.png)

![image-20210809201424148](/Users/chris/Library/Application Support/typora-user-images/image-20210809201424148.png)

![image-20210809201433960](/Users/chris/Library/Application Support/typora-user-images/image-20210809201433960.png)

### 动态代理

- 动态代理和静态代理角色一样
- 动态代理的类是动态生成的，不是我们直接写好的
- 动态分类分为两大类：基于接口的
  - 基于接口的：jdk动态代理
  - 基于类的：c g li b
  - java字节码实现：javasist

需要了解两个类：Proxy，invocationHandler

## invocationHandler

![image-20210809213904249](/Users/chris/Library/Application Support/typora-user-images/image-20210809213904249.png)

