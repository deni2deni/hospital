package com.academy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/redirect", true)
                .and()
                .authorizeRequests()
                .antMatchers("/redirect").hasAnyRole("USER","ADMIN","NURSE","DOCTOR")
                .antMatchers("/").hasAnyRole("USER","ADMIN","NURSE","DOCTOR")
                .antMatchers("/registration").permitAll()
                .antMatchers("/patientPage").hasRole("USER")
                .antMatchers("/myHistory").hasRole("USER")
                .antMatchers("/addCard").hasRole("USER")
                .antMatchers("/doctorPage").hasAnyRole("NURSE","DOCTOR")
                .antMatchers("/admissionConfirm").hasRole("DOCTOR")
                .antMatchers("/patients").hasAnyRole("NURSE","DOCTOR")
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}