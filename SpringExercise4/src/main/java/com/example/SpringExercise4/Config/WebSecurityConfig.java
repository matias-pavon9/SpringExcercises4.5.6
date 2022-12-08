package com.example.SpringExercise4.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers("/laptop/all").permitAll()
                .antMatchers("/laptop/oneById").permitAll()
                .antMatchers("/helloSecurity").hasRole("ADMIN")
                .antMatchers("/laptop/create").hasRole("ADMIN")
                .antMatchers("/laptop/update").hasRole("ADMIN")
                .antMatchers("/laptop/delete").hasRole("ADMIN")
                .antMatchers("/laptop/deleteAll").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("Matias").password(passwordEncoder().encode("1234")).roles("USER")
                .and()
                .withUser("MatiasSecurity").password(passwordEncoder().encode("1234")).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
