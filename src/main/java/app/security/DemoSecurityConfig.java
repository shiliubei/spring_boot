package app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {


//    private UserDetailsService userDetailsService;
//    private AuthenticationSuccessHandler successHandler;
//
//    public DemoSecurityConfig(@Qualifier("myUserDetailsService") UserDetailsService userDetailsService, AuthenticationSuccessHandler successHandler) {
//        this.userDetailsService = userDetailsService;
//        this.successHandler = successHandler;
//    }
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin"))
                .authorities("ROLE_ADMIN");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        http
//                .authorizeRequests()
//                .antMatchers("/login")
//                .anonymous()
//                .antMatchers("/user")
//                .hasRole("USER")
//                .antMatchers("/admin/**")
//                //.permitAll()
//                .authenticated()
//                //.hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login") //custom login page
//                .loginProcessingUrl("/authenticateTheUser") //Login form should POST data this URL for processing (check user id and password)
//                .successHandler(successHandler)
//        ;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
               // .and()
               // .httpBasic()
        ;
    }


    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();

        // admin
        UserDetails admin = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        users.add(admin);

        return new InMemoryUserDetailsManager(users);
    }

}