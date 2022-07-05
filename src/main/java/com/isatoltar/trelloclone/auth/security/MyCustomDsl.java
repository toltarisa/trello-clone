package com.isatoltar.trelloclone.auth.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder
            .cors().and().csrf().disable()
            .authorizeRequests((auth) -> auth.antMatchers("/login", "/register").permitAll().anyRequest().authenticated())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }

    @Override
    public void configure(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        http
                .addFilter(new JwtAuthenticationFilter(authenticationManager))
                .addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public static MyCustomDsl customDsl() {
        return new MyCustomDsl();
    }
}
