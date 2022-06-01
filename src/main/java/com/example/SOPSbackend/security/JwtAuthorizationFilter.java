package com.example.SOPSbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthorizationFilter implements Filter {
    private final static String TOKEN_HEADER = "Authorization";

    private final UserService userService;
    private final String tokenSecret;

    public JwtAuthorizationFilter(UserService userService, String tokenSecret) {
        this.userService = userService;
        this.tokenSecret = tokenSecret;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication((HttpServletRequest) request);
        if(authentication != null)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        if(token == null)
            return null;

        UserDetails userDetails = verifyTokenAndLoadUser(token);
        if(userDetails == null)
            return null;

        return new UsernamePasswordAuthenticationToken(userDetails, null,
                                                       userDetails.getAuthorities());
    }

    private UserDetails verifyTokenAndLoadUser(String token) {
        try {
            DecodedJWT decodedToken = JWT.require(Algorithm.HMAC256(tokenSecret))
                                         .build()
                                         .verify(token);

            String username = decodedToken.getSubject();
            Role role = Role.fromName(decodedToken.getClaim("role").asString());
            return role == null ? null :
                userService.loadUserByUsername(role, username);
        } catch(Exception e) {
            return null;
        }
    }
}