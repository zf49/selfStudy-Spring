import com.kuang.dao.UserDao;
import com.kuang.dao.UserDaoOracleImpl;
import com.kuang.service.UserService;
import com.kuang.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {

        //拿到spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //需要什么get什么

       UserServiceImpl serive = (UserServiceImpl) context.getBean("serive");

        serive.getUser();



    }
}
