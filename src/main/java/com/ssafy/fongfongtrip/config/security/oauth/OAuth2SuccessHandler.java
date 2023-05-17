package com.ssafy.fongfongtrip.config.security.oauth;

import java.io.IOException;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.config.security.jwt.JwtSetupService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final JwtSetupService jwtSetupService;

    @Value("${client.url}")
    private String clientUrl;

    @Value("${client.endpoint}")
    private String redirectEndPoint;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String authToken = jwtSetupService.createAuthToken(loginUser);
        getRedirectStrategy().sendRedirect(request, response, clientUrl + redirectEndPoint + "?token=" + authToken);
    }

}
