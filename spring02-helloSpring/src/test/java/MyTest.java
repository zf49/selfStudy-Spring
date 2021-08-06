import com.kuang.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {




    public static void main(String[] args) {

         //获取上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象都在spring中了，如果要使用，直接去里面取出来就行了

        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
        

    }


    
}
