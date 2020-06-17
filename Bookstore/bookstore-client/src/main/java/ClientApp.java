import UserInterface.Console;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientApp {
    public static final String URL ="http://localhost:8080/api/books";

    public static void main(String[] args){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(
                "Configuration"
        );
        Console c=context.getBean(Console.class);
        c.run();
    }
}
