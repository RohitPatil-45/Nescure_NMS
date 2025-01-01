package npm.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
            .antMatchers("/login/index").permitAll() // Allow access to index.html
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login/index")
            .permitAll()
            .and()
        .sessionManagement()
            .invalidSessionUrl("/login/index?timeout=true"); // Redirect to index.html on session timeout
    }
}
