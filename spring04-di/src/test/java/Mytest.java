import com.kuang.pojo.Student;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");


      Student student = (Student) context.getBean("student");

        System.out.println(student);

    }


     @Test
    public void userTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("user.xml");

         User user = context.getBean("user2", User.class);
         User user2 = context.getBean("user2", User.class);

         System.out.println(user==user2);

     }
    



}
