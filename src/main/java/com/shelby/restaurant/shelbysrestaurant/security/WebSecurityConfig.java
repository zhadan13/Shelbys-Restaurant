package com.shelby.restaurant.shelbysrestaurant.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(name = "security.enabled", havingValue = "true", matchIfMissing = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/actuator/**", "/v3/api-docs", "/swagger-ui/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
