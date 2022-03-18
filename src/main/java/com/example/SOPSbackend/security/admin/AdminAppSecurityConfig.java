package com.example.SOPSbackend.security.admin;

import com.example.SOPSbackend.response.auth.CustomUnauthorizedEntryPoint;
import com.example.SOPSbackend.security.JsonObjectAuthenticationFilter;
import com.example.SOPSbackend.security.JwtAuthorizationFilter;
import com.example.SOPSbackend.security.RestAuthenticationFailureHandler;
import com.example.SOPSbackend.security.RestAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class AdminAppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AdminUserService adminUserService;
    private final ObjectMapper objectMapper;
    private final RestAuthenticationSuccessHandler successHandler;
    private final RestAuthenticationFailureHandler failureHandler;
    private final String tokenSecret;

    public AdminAppSecurityConfig(PasswordEncoder passwordEncoder, AdminUserService adminUserService,
                                  ObjectMapper objectMapper, RestAuthenticationSuccessHandler successHandler,
                                  RestAuthenticationFailureHandler failureHandler,
                                  @Value("${jwt.secret}") String tokenSecret) {
        this.passwordEncoder = passwordEncoder;
        this.adminUserService = adminUserService;
        this.objectMapper = objectMapper;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.tokenSecret = tokenSecret;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
            .antMatcher("/admin/**")
            .authorizeRequests()
            .anyRequest().authenticated()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .addFilter(adminAuthenticationFilter())
            .addFilter(adminAuthorizationFilter())
            .exceptionHandling()
            .authenticationEntryPoint(new CustomUnauthorizedEntryPoint());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminUserService);
        provider.setPasswordEncoder(passwordEncoder);
        auth.authenticationProvider(provider);
    }

    @Bean(name="AuthenticationAdminFilter")
    public JsonObjectAuthenticationFilter adminAuthenticationFilter() throws Exception {
        var authFilter = new JsonObjectAuthenticationFilter(objectMapper);
        authFilter.setFilterProcessesUrl("/admin/login");
        authFilter.setUsernameParameter("email");
        authFilter.setAuthenticationSuccessHandler(successHandler);
        authFilter.setAuthenticationFailureHandler(failureHandler);
        authFilter.setAuthenticationManager(super.authenticationManager());
        return authFilter;
    }

    @Bean(name="AuthorizationAdminFilter")
    public JwtAuthorizationFilter adminAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(super.authenticationManager(),
                adminUserService, tokenSecret);
    }
}
