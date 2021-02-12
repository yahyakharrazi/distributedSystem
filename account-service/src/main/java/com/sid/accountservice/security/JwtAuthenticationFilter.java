package com.sid.accountservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sid.accountservice.entities.Compte;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        Compte compte;
        try {
            compte = new ObjectMapper().readValue(request.getInputStream(), Compte.class);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        return authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                compte.getUsername(),
                compte.getPassword()
            )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User springUser = (User) authResult.getPrincipal();
        String jwtToken = Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+ com.example.cinema.security.SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, com.example.cinema.security.SecurityConstants.JWT_SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact();
        response.addHeader(com.example.cinema.security.SecurityConstants.TOKEN_HEADER, com.example.cinema.security.SecurityConstants.TOKEN_PREFIX+jwtToken);
    }
}
