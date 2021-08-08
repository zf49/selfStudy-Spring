import com.kuang.config.ConfigTest;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
