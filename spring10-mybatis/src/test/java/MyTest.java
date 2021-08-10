import com.kuang.dao.UserMapper;
import com.kuang.dao.UserMapperImpl;
import com.kuang.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {

     @Test
    public void test1() throws IOException {


         ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
         UserMapper getUserList = context.getBean("getUserList", UserMapper.class);
         for (User user : getUserList.getUserList()) {
             System.out.println(user);
         }

     }


     @Test
     public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

         UserMapper support = context.getBean("support", UserMapper.class);

         for (User user : support.getUserList()) {
             System.out.println(user);
         }


     }


}
