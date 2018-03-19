package web.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import web.servlet.auth.AuthorizationServlet;
import web.servlet.get.FindAllRequisitionsServlet;
import web.servlet.page.DashboardPageServlet;
import web.servlet.page.IndexPageServlet;

import java.util.Arrays;

/**
 * Created by EGBoldyr on 16.03.18.
 */

@Configuration
public class ServletRegistrationConfig {

    @Bean
    public ServletRegistrationBean pageIndex() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new IndexPageServlet());
        bean.setUrlMappings(Arrays.asList("/index.html"));
        return bean;
    }

    @Bean
    public ServletRegistrationBean pageDashboard() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new DashboardPageServlet());
        bean.setUrlMappings(Arrays.asList("/dashboard.html"));
        return bean;
    }

    @Bean
    public ServletRegistrationBean servletAuthorization() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new AuthorizationServlet());
        bean.setUrlMappings(Arrays.asList("/authorization"));
        return bean;
    }

    @Bean
    public ServletRegistrationBean servletAllRequisitions() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new FindAllRequisitionsServlet());
        bean.setUrlMappings(Arrays.asList("/all_requisitions"));
        return bean;
    }
}
