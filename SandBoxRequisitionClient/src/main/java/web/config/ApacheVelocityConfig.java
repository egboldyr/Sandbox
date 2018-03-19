package web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.annotation.PostConstruct;
import javax.servlet.Servlet;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@Configuration
@ConditionalOnClass({Servlet.class})
public class ApacheVelocityConfig {

    private ResourceLoader resourceLoader;

    @PostConstruct
    private void initialize() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Bean
    public VelocityConfig config() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        configurer.setResourceLoader(resourceLoader);
        return configurer;
    }

    @Bean
    public ViewResolver viewResolver() {
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setViewClass(VelocityToolboxView.class);
        resolver.setPrefix("/templates/vm/");
        resolver.setSuffix(".vm");
        return resolver;
    }
}
