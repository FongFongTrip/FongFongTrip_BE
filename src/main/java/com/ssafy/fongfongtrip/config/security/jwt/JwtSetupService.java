package com.ssafy.fongfongtrip.config.security.jwt;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtSetupService {

	private final JwtProvider jwtProvider;

    @Value("${client.host}")
    private String clientHost;

    @Value("${jwt.access-header}")
    private String accessTokenHeaderTag;

    @Value("${jwt.refresh-header}")
    private String refreshTokenHeaderTag;

    public String addJwtTokensToCookie(HttpServletResponse response, LoginUser loginUser) {
        JwtToken jwtToken = jwtProvider.createJwtToken(loginUser);

        ResponseCookie accessTokenCookie = setCookie(accessTokenHeaderTag, jwtToken.getAccessToken());
        ResponseCookie refreshTokenCookie = setCookie(refreshTokenHeaderTag, jwtToken.getRefreshToken());
        log.info("accessToken = " + accessTokenCookie);
        log.info("refreshToken = " + refreshTokenCookie);
        response.addHeader("Set-Cookie", accessTokenCookie.toString());
        response.addHeader("Set-Cookie", refreshTokenCookie.toString());
        
        return jwtToken.getAccessToken();
    }
    
    public String createAuthToken(LoginUser loginUser) {
    	return jwtProvider.createAuthToken(loginUser);
    }
    
    public JwtToken createJwtTokenByAuthToken(String authToken) throws NumberFormatException {
    	return jwtProvider.createJwtTokenByAuthToken(authToken);
    }

    private ResponseCookie setCookie(String key, String value) {
        return ResponseCookie.from(key, value)
                .path("/")
                .domain(clientHost)
                .build();
    }
}
