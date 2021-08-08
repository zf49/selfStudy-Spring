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
   
       <context:annotation-config/>
   
   </beans>
```             

## 注解说明
- @Autowired: 自动装配通过类型，名字
如果Autowired的自动装配环境比较复杂，则使用@Qualifier(value="xxx")来实现

- @Nullable: 字段标记了这个注解，则该字段可以为null

- @Resource: 先通过名字，后通过类型来自动装配


