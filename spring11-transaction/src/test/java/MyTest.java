import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    
      @Test
    public void test1(){
          ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

          UserMapper bean =  context.getBean("1111",UserMapper.class);
          for (User user : bean.getUserList()) {
              System.out.println(user);
          }
          

      }


}
