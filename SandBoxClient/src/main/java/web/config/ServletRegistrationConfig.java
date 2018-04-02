package web.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import web.servlet.auth.AuthorizationServlet;

import java.util.Arrays;

/**
 * Created by EGBoldyr on 16.03.18.
 */

@Configuration
public class ServletRegistrationConfig {

    @Bean
    public ServletRegistrationBean servletAuthorization() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new AuthorizationServlet());
        bean.setUrlMappings(Arrays.asList("/authorization"));
        return bean;
    }
}
