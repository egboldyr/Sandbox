package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@SpringBootApplication
@ComponentScan("web")
public class SandBoxRequisitionClient {

    public static void main(String[] args) {
        SpringApplication.run(SandBoxRequisitionClient.class, args);
    }
}
