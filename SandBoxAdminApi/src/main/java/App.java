import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by EGBoldyr on 10.05.18.
 */

@SpringBootApplication
@ComponentScan("rest")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
