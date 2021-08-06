import com.kuang.pojo.User;
import com.kuang.pojo.User1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {

        //spring容器，类似于婚介网站！
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        User1 user = (User1) context.getBean("u2");

        user.show();
    }
}
