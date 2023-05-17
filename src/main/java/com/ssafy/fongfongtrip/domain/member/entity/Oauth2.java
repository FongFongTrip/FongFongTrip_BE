package com.ssafy.fongfongtrip.domain.member.entity;

import com.ssafy.fongfongtrip.config.security.oauth.mapper.AuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Oauth2 {
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(nullable = false, unique = true)
    private String accountId;

    public Oauth2(AuthProvider authProvider, String accountId) {
        this.authProvider = authProvider;
        this.accountId = accountId;
    }
}