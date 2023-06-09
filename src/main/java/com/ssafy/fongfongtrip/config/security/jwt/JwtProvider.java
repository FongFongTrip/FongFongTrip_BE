package com.ssafy.fongfongtrip.config.security.jwt;

import java.security.Key;
import java.util.Date;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {
	private final Key key;

	private static final Long AUTH_TOKEN_VALIDATION_SECOND = 60L * 1000;
    private static final Long ACCESS_TOKEN_VALIDATION_SECOND = 60L * 60 * 24 * 1000;
    private static final Long REFRESH_TOKEN_VALIDATION_SECOND = 60L * 60 * 24 * 14 * 1000;
    private static final String BEARER_TYPE = "bearer";
    
    private final JwtValidator jwtValidator;

    public JwtToken createJwtToken(LoginUser loginUser) {
        Claims claims = getClaims(loginUser);

        String accessToken = getToken(loginUser, claims, ACCESS_TOKEN_VALIDATION_SECOND);
        String refreshToken = getToken(loginUser, claims, REFRESH_TOKEN_VALIDATION_SECOND);

        return new JwtToken(accessToken, refreshToken, BEARER_TYPE);
    }
    
    public String createAuthToken(LoginUser loginUser) {
    	Claims claims = getClaims(loginUser);
    	return getToken(loginUser, claims, AUTH_TOKEN_VALIDATION_SECOND);
    }
    
    public JwtToken createJwtTokenByAuthToken(String authToken) throws NumberFormatException, EntityNotFoundException {
    	return createJwtToken(jwtValidator.getLoginUser(authToken));
    }

    public Claims getClaims(LoginUser loginUser) {
        Claims claims = Jwts.claims();
        claims.put("id", loginUser.getName());
        return claims;
    }

    private String getToken(LoginUser loginUser, Claims claims, Long validationSecond) {
        long now = new Date().getTime();

        return Jwts.builder()
                .setSubject(loginUser.getName())
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(now + validationSecond))
                .compact();
    }
}
