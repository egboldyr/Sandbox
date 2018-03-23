package soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by EGBoldyr on 14.03.18.
 */

@SpringBootApplication
@EnableCaching
@ComponentScan("soap")
public class SandboxServerBoot {

    public static void main(String[] args) {
        SpringApplication.run(SandboxServerBoot.class, args);
    }
}
