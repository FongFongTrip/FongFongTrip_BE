package com.ssafy.fongfongtrip.config.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtValidator jwtValidator;

    @Value("${jwt.access-header}")
    private String accessTokenHeaderTag;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> token = Optional.ofNullable(getTokensFromHeader(request));
        
        token.ifPresent(
                t -> {
                	log.info("AccessToken: {}", t);
                	Authentication authentication = jwtValidator.getAuthentication(t);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
        filterChain.doFilter(request, response);
    }
    
    private String getTokensFromHeader(HttpServletRequest request) {
    	return request.getHeader(accessTokenHeaderTag);
    }

    private String getTokensFromCookies(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }

        Optional<Cookie> accessCookie = getAccessToken(cookies);
        return accessCookie.map(Cookie::getValue).orElse(null);
    }

    private Optional<Cookie> getAccessToken(Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(accessTokenHeaderTag))
                .findFirst();
    }
}
