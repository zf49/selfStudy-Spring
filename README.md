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

4. 自动装配

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

