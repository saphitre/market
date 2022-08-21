package com.example.market.config;

import com.example.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *Класс, определяющий конфигурацию безопасности
 * @author saphitre
 * @version 1.0
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Поле типа UserService
     * @see UserService
     */
    private UserService userService;

    /**
     * Конструктор класса, создающий бин объекта UserService
     * @param userService
     */
    @Autowired
    public void setSecurityConfig(UserService userService){
        this.userService=userService;
    }

    /**
     * Метод, определяющий текущую конфигурацию безопасности проекта
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
    }

    /**
     * Метод настройки проводника аутентификации
     * @see DaoAuthenticationProvider
     * @return настроенный объект класса DaoAutenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    /**
     * Фабричный метод энкодера пароля
     * @see BCryptPasswordEncoder,PasswordEncoder
     * @return возвращает энкодер пароля проекта
     */
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

}
