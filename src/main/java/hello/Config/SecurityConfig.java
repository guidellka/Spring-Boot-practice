package hello.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/login").permitAll()
                .antMatchers("/student/**","/admin ").hasAnyRole("ADMIN")
                .antMatchers("/demo/**","/user").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successHandler);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("1234")).roles("USER");
//        auth.authenticationProvider()

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
