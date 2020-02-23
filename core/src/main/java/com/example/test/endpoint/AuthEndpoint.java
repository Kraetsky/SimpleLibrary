package com.example.test.endpoint;

import com.example.test.domain.User;
import com.example.test.domain.request.AuthRequest;
import com.example.test.domain.response.AuthResponse;
import com.example.test.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class AuthEndpoint {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private static final Long TOKEN_EXPIRATION_TIME = 120L;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest request) {
        Authentication authentication;
        try {
            authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch (AuthenticationException ae) {
            throw new PreAuthenticatedCredentialsNotFoundException("The combination of login and password is not valid.");
        }
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(AuthResponse.builder()
                            .expiresIn(TOKEN_EXPIRATION_TIME)
                            .userId(user.getId())
                            .idToken(jwtUtils.generate(user.getId()))
                            .build());
        } else {
            log.error("User not found, login: {}.", request.getLogin());
            throw new PreAuthenticatedCredentialsNotFoundException("The combination of login and password is not valid.");

        }

    }
}
