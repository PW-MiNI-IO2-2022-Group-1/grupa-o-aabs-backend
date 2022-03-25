package com.example.SOPSbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final static String TOKEN_HEADER = "Authorization";

    private final UserDetailsService userService;
    private final String tokenSecret;

    public JwtAuthorizationFilter(AuthenticationManager authManager, UserDetailsService userService,
                                  String tokenSecret) {
        super(authManager);
        this.userService = userService;
        this.tokenSecret = tokenSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        if(token == null)
            return null;

        UserDetails userDetails = verifyTokenAndLoadUser(token);
        if(userDetails != null)
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null,
                                                               userDetails.getAuthorities());
        else
            return null;
    }

    private UserDetails verifyTokenAndLoadUser(String token) {
        try {
            DecodedJWT decodedToken = JWT.require(Algorithm.HMAC256(tokenSecret))
                                         .build()
                                         .verify(token);

            String username = decodedToken.getSubject();
            if (username != null)
                return userService.loadUserByUsername(username);

        } catch(Exception e) {
            return null;
        }
        return null;
    }
}