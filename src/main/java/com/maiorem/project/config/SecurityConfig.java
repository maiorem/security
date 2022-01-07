package com.maiorem.project.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 사용자 계정 user1
        auth.inMemoryAuthentication().withUser("user1")
                // password 1111 인코딩 결과
                .password("$2a$10$FCMJNNaiKdKziZppY4hFN.Soq.9hIA.GJ1XbVxijyGzXPJD1Jo68e")
                .roles("USER");
    }
}
