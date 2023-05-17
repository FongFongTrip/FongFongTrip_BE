package com.ssafy.fongfongtrip.domain.member.dto.response;

import com.ssafy.fongfongtrip.config.security.jwt.JwtToken;

public record AuthResponse(String accessToken,
                           String refreshToken) {

    public static AuthResponse of(JwtToken jwtToken) {
        return new AuthResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
    }
}
