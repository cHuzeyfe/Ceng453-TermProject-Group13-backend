package com.monopoly.util;

import com.monopoly.service.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    @Value("${jwt.secretKey}")
    private String secretKey;

    private final MyUserDetailService myUserDetailService;

    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader("Authorization");
        if(authorization!=null && authorization.startsWith("Bearer")){
            String jwtToken = authorization.substring(7);
            String username = jwtUtil.extractUsername(jwtToken,secretKey);
            UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
            var token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
