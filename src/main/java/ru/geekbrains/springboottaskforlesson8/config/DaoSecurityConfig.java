package ru.geekbrains.springboottaskforlesson8.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.geekbrains.springboottaskforlesson8.services.UserService;

@EnableWebSecurity(debug = true)
public class DaoSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(DaoSecurityConfig.class.getName());

    public DaoSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Dao Authentication Provider");
        http.authorizeRequests()
                .antMatchers("/api/v1/**").authenticated()                                                                  //доступ для всех зарегистрированных
                .antMatchers("/user/**", "/inc", "/dec", "/current", "/h2-console/**").hasAnyRole("ADMIN", "USER")   //доступ для всех ролей
                .antMatchers("/score/get/**").hasAnyRole("ADMIN")                                                   //достпу для роли АДМИН
                .anyRequest().permitAll().and().formLogin();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
