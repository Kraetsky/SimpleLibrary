package com.example.test.security;

import com.example.test.domain.User;
import com.example.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        String token = request.getHeader("Authentication");
        if (token != null) {
            if (jwtUtils.validate(token)) {
                User user = userService.getById(jwtUtils.getUserId(token).orElseThrow(() ->
                        new RuntimeException("User id couldn't be found in the authentication token.")));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        token,
                        Collections.singleton(new SimpleGrantedAuthority("library")));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
