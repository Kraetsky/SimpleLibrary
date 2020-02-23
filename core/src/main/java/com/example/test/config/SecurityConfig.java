package com.example.test.config;

import com.example.test.security.AuthFilter;
import com.example.test.security.JwtUtils;
import com.example.test.security.LoginPasswordAuthenticationProvider;
import com.example.test.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginPasswordAuthenticationProvider authenticationProvider;

    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(authCookieFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().frameOptions().disable();
    }

    private AuthFilter authCookieFilter() {
        UserService userService = getApplicationContext().getBean(UserService.class);
        return new AuthFilter(jwtUtils(), userService);
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils(RandomStringUtils.random(256, "simpleLibrary"));
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            private ObjectMapper objectMapper = new ObjectMapper();

            @Override
            public void commence(HttpServletRequest request,
                                 HttpServletResponse response,
                                 AuthenticationException authException) throws IOException {
                response.setHeader("Content-Type", "application/json");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getOutputStream().print(
                        objectMapper.writeValueAsString(
                                new Error(authException.getMessage())));
            }
        };
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
