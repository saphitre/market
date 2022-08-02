package com.example.market.config;

import com.example.market.model.entity.Role;
import com.example.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public void setSecurityConfig(UserService userService){
        this.userService=userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/save","/deleteById/{product_id}").hasAuthority("ADMIN")
                .antMatchers("/searchById/{product_id}","/").permitAll()
                .and()
                .httpBasic()
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService(){
//        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(10);
//        UserDetails userDetails = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$X..vqVo7z2jdpSDho/pMDewkw6DB.e1e/.T5.fU0Izx9RtkihU6EC")
//                .roles(Role.ADMIN.name())
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
//        UserDetails userDetails = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$X..vqVo7z2jdpSDho/pMDewkw6DB.e1e/.T5.fU0Izx9RtkihU6EC")
//                .roles("ADMIN")
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(userDetails);
//        return jdbcUserDetailsManager;
//    }
}
