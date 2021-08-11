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

----

## AOP

![image-20210810171008181](/Users/chris/Library/Application Support/typora-user-images/image-20210810171008181.png)

![image-20210810171027573](/Users/chris/Library/Application Support/typora-user-images/image-20210810171027573.png)

![image-20210810171217070](/Users/chris/Library/Application Support/typora-user-images/image-20210810171217070.png)

![image-20210810171234955](/Users/chris/Library/Application Support/typora-user-images/image-20210810171234955.png)

## Spring 实现AOP-(moudle09)

倒包

```xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.8.M1</version>
    <scope>runtime</scope>
</dependency>

```

1. 使用spring接口

```xml
<!--注册bean    z-->
     <bean id="userService" class="com.kuang.service.UserServiceImpl"/>
     <bean id="log" class="com.kuang.log.Log"/>
     <bean id="afterLog" class="com.kuang.log.AfterLog"/>


<!-- 方式一 使用原生aoi接口 -->
<!--    配置AOP 倒入aop约束-->
     <aop:config>
<!--         找到切入点

expression: 表达式
execution(要是行的位置* * * * *)
-->
      <aop:pointcut id="pointcut" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>
<!--执行环绕 增强-->

         <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
         <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
        </aop:config>

```

方法2： 使用自定义类来实现

主要定义切面

```xml
<!--方式二      f-->

    <bean id="diy" class="com.kuang.diy.PointCut"/>
     <aop:config>
<!--         自定义切面  ref引用的类即上面的bean  -->
         <aop:aspect ref="diy">
<!--                切入点-->
               <aop:pointcut id="pointcut" expression="execution(* com.kuang.service.UserServiceImpl.*(..))"/>
<!--             通知advice-->
             <aop:before method="before" pointcut-ref="pointcut"/>
             <aop:after method="after" pointcut-ref="pointcut"/>
         </aop:aspect>

     </aop:config>

```

方法3: 使用注解实现

```xml
<!--    方式3-->
  <bean id="annotationPointCut" class="com.kuang.diy.AnnotationPointCut"/>
<!--开启注解支持   k-->
    
     <aop:aspectj-autoproxy/>
    
```

```java
package com.kuang.diy;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//使用注解来实现
@Aspect  //标注这是一个切面
public class AnnotationPointCut {

    @Before("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("方法执行前");
    }

     @After("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("方法执行hou");
    }

     @Around("execution(* com.kuang.service.UserServiceImpl.*(..))")
     //环绕中，我们需要给定一个参数，代表我们要获取处理切入的点
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
         System.out.println(jp.getSignature());
         Object proceed = jp.proceed();
         System.out.println("环绕后");

     }


}
```

```xml

环绕前
void com.kuang.service.UserService.add()
方法执行前
add用户
方法执行hou
环绕后

```

## 整合Mybatis

步骤

1. 倒入jar包

   - junit

   - mybatis
   - mysql
   - Spring
   - aop织入
   - mybatis-spring

```xml
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.46</version>
        </dependency>
        
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>


        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.9</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.9</version>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.8.M1</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.6</version>
        </dependency>
        
    </dependencies>
```



2. 编写配置文件

3. 测试

### 回忆Mybatis

1. 编写实体类

2. 编写核心配置文件

   ```xm
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="xxxx">
   
   
   
       
   </mapper>
   ```

   ```xml
   
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
   
        <properties resource="db.properties"/>
       <typeAliases>
          <package name="com.kuang.pojo"/> 
       </typeAliases>
   
       <environments default="development">
           <environment id="development">
               <transactionManager type="JDBC"/>
               <dataSource type="POOLED">
                   <property name="driver" value="${driver}"/>
                   <property name="url" value="${url}"/>
                   <property name="username" value="${username}"/>
                   <property name="password" value="${password}"/>
               </dataSource>
           </environment>
       </environments>
   
   
       <mappers>
            <mapper class="com.kuang.pojo.User"/>
       </mappers>
   
   
   </configuration>
   
   
   
   
   
   driver=com.mysql.jdbc.Driver
   url=jdbc:mysql://127.0.0.1:3306/mybatis
   username=root
   password=wzf!@#000
   
   
   
   ```

   

3. 编写接口

   ```java
   package com.kuang.dao;
   
   import com.kuang.pojo.User;
   
   import java.util.List;
   
   public interface UserMapper {
   
       List<User> getUserList();
   
   
   }
   ```

   

4. 标写Mapper.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="com.kuang.dao.UserMapper">
   
       <select id="getUserList" resultType="user">
           select * from mybatis.user;
       </select>
   
       
   </mapper>
   ```

   

5. 测试

   ```java
   public class MyTest {
   
        @Test
       public void test1() throws IOException {
   
             String resource = "mybatis-config.xml";
            InputStream resourceAsReader = Resources.getResourceAsStream(resource);
   
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
   
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
   
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
            
            sqlSession.close();
   
        }
   
   
   }
   ```

   

### 整合

spring 配置文件

```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">

<!--  Datasource  使用spring数据源替换Mybatis的配置
    我们这里使用spring 提供的jdbc org.springframework.jdbc.datasource.DriverManagerDataSource
-->

     <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
       <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
         <property name="username" value="root"/>
         <property name="password" value="wzf!@#000"/>
         <property name="url" value="jdbc:mysql://127.0.0.1:3306/mybatis"/>
     </bean>
<!--    sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource" />
<!--        绑定mybatis-->
         <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/kuang/dao/UserMapper.xml"/>
    </bean>


    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
<!--        只能通过构造起注入
因为SqlSessionTemplate没有set方法
-->
       <constructor-arg index="0" ref="sqlSessionFactory"/>
  </bean>


    

</beans>
```

1. 编写数据源配置

2. sqlSessionFactory

3. sqlSessionTemplate(或者继承SqlSessionDaoSupport)

   ![image-20210811010142984](/Users/chris/Library/Application Support/typora-user-images/image-20210811010142984.png)

4. 需要给接口加实现类

5. 将自己的实现类，注入到Spring中

![image-20210811005937746](/Users/chris/Library/Application Support/typora-user-images/image-20210811005937746.png)

-------

## 声明式事务

要么都成功，要么都失败

事务涉及到数据完整性和一致性的问题

### ACID

- 原子性

- 一致性

- 隔离性

  - 多个业务操作同一个资源，防止数据损坏

- 持久性

  - 事务一旦提交，结果都不会被影响，被持久化的存储到存储器中

  Spring 容器中配置

```xml
<!--    结合aop实现事务-->
<!--配置事务类      p-->

    <tx:advice id="txAdvice" transaction-manager="transactionManager">
<!--        给那些方法配置事务-->
<!--        配置事务的传播特性: new propagation = required-->
        <tx:attributes>

            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="query" read-only="true"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="*" propagation="REQUIRED"/>

        </tx:attributes>
    </tx:advice>

<!--   配置事务切入-->
    <aop:config>
         <aop:pointcut id="txPointCut" expression="execution(* com.kuang.mapper.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
    </aop:config>
```



## 自动装配

![image-20210811181406164](/Users/chris/Library/Application Support/typora-user-images/image-20210811181406164.png)
