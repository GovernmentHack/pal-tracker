package io.pivotal.pal.tracker;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String forceHttps = System.getenv("SECURITY_FORCE_HTTPS");
        if(forceHttps != null && forceHttps.toLowerCase().equals("true")){
            http.authorizeRequests().antMatchers("/**").hasRole("USER").and().httpBasic()
                    .and().requiresChannel().anyRequest().requiresSecure();
        } else {
            http.authorizeRequests().antMatchers("/**").hasRole("USER").and().httpBasic();
        }
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}