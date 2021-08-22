package vn.hvcg.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import vn.hvcg.security.domain.UserServiceDetailImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Run -> log like following:
    // Using generated security password: 075c3bbc-037a-45ec-be82-80c3d7849261

    // Username: user
    // Password: 075c3bbc-037a-45ec-be82-80c3d7849261
    @Override
    protected void configure(HttpSecurity http) throws  Exception {
       http.authorizeRequests()
       .antMatchers("/login", "/register", "/forbidden").permitAll()
       .antMatchers("/ui-student").hasAnyRole("Admin")
       .anyRequest()   .hasAnyRole("User", "Admin")
                       .and().formLogin()//.loginPage("/login").failureUrl("/login-error")
                       .and().exceptionHandling().accessDeniedPage("/forbidden");
    }

    // In-Memory Authentication
    // Username: user/ admin
    // Password: userpass/ adminpass
    /*
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
            .withUser("user").password("{noop}userpass").roles("User")
            .and().withUser("admin").password("{noop}adminpass").roles("Admin");
    }
    */

    // JDBC Authentication
    // Username: admin
    // Password: password
    // Run TestClass to get encoded password for admin, after that insert into Database
    /*
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceDetailImpl userServiceDetail;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userServiceDetail).passwordEncoder(passwordEncoder);
    }
     */

    //AuthenticationProvider
    // Username: admin
    // Password: password
    @Autowired
    CustomAuthenticationProvider authenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authenticationProvider);
    }

}