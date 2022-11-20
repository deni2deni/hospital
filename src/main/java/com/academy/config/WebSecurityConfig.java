package com.academy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        String[] staticResources = {
          "/css/**"
        };
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/").permitAll()
                        .antMatchers("/css/*").permitAll()
                        .antMatchers("/test").hasAuthority("doctor")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public JdbcUserDetailsManager users(){
        var jdbcUserDetailManager = new JdbcUserDetailsManager();
        jdbcUserDetailManager.setDataSource(dataSource);
        jdbcUserDetailManager.setUsersByUsernameQuery("select email, password, status from login where email=?");
        jdbcUserDetailManager.setAuthoritiesByUsernameQuery("select login.email, role.name from login inner join user on login.user_id = user.id inner join role on role.id = user.role_id where login.email=?");
        return jdbcUserDetailManager;
    }

    @Bean
    public PasswordEncoder getPassword(){
        return NoOpPasswordEncoder.getInstance();
    }
}