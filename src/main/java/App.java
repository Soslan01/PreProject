import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean1 = (HelloWorld) applicationContext.getBean("HelloWorld");
        System.out.println(bean1.getMessage());

        HelloWorld bean2 = (HelloWorld) applicationContext.getBean("HelloWorld");

        System.out.println(bean2.getMessage());

        System.out.println(bean1 == bean2);
        System.out.println(bean1);
        System.out.println(bean2);

        Cat beanCat = (Cat) applicationContext.getBean("cat");
        System.out.println(beanCat.getMessage());

        Cat beanCat2 = (Cat) applicationContext.getBean("cat");
        System.out.println(beanCat.getMessage());
        System.out.println(beanCat == beanCat2);
    }
}