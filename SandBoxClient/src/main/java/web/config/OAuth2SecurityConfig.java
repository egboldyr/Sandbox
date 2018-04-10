package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by EGBoldyr on 10.04.18.
 */

@Configuration
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager manager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.requestMatchers()
                .antMatchers("/**")
                .and()
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("manager")
                .and()
                    .formLogin()
                    .loginPage("/index.html")
                    .defaultSuccessUrl("/dashboard.html", true)
                    .permitAll(true)
                .and()
                    .logout()
                    .logoutSuccessUrl("/index.html?logout")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(manager)
                .inMemoryAuthentication()
                .withUser("root")
                .password("root")
                .roles("manager");
    }
}
