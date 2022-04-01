package com.example.SOPSbackend.security.config;

import com.example.SOPSbackend.model.BasicUser;
import com.example.SOPSbackend.response.CustomUnauthorizedEntryPoint;
import com.example.SOPSbackend.security.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserSecurityConfig<T extends BasicUser> extends WebSecurityConfigurerAdapter {
    protected final String directory;
    protected final PasswordEncoder passwordEncoder;
    protected final BasicUserService<T> userService;
    protected final ObjectMapper objectMapper;
    protected final RestAuthenticationSuccessHandler successHandler;
    protected final RestAuthenticationFailureHandler failureHandler;
    protected final String tokenSecret;

    public UserSecurityConfig(String directory, PasswordEncoder passwordEncoder, BasicUserService<T> userService,
                              ObjectMapper objectMapper, RestAuthenticationSuccessHandler successHandler,
                              RestAuthenticationFailureHandler failureHandler,
                              String tokenSecret) {
        this.directory = directory;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.tokenSecret = tokenSecret;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/" + directory + "/hello")
                .permitAll()
            .and()
                .antMatcher("/" + directory + "/**")
                .authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilter(createAuthenticationFilter())
                .addFilter(createAuthorizationFilter())
                .exceptionHandling()
                .authenticationEntryPoint(new CustomUnauthorizedEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);

        auth.authenticationProvider(provider);
    }

    private JsonObjectAuthenticationFilter createAuthenticationFilter() throws Exception {
        var authFilter = new JsonObjectAuthenticationFilter(objectMapper);
        authFilter.setFilterProcessesUrl("/" + directory + "/login");
        authFilter.setUsernameParameter("email");
        authFilter.setAuthenticationSuccessHandler(successHandler);
        authFilter.setAuthenticationFailureHandler(failureHandler);
        authFilter.setAuthenticationManager(super.authenticationManager());
        return authFilter;
    }

    public JwtAuthorizationFilter createAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(super.authenticationManager(),
                userService, tokenSecret);
    }
}