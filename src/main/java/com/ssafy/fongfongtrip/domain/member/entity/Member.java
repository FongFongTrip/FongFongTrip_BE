package com.ssafy.fongfongtrip.domain.member.entity;

import com.ssafy.fongfongtrip.config.security.oauth.mapper.AuthProvider;
import com.ssafy.fongfongtrip.global.audit.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String loginId;
    private String password;

    @Column(length = 30, nullable = false)
    private String nickname;
    private String email;
    private String phone;

    @Embedded
    private Oauth2 oauth2;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> role = new ArrayList<>(List.of(Role.ROLE_USER));

    public Member(String nickname, Oauth2 oauth2) {
        this.nickname = nickname;
        this.oauth2 = oauth2;
    }

    public List<SimpleGrantedAuthority> getRole() {
        return role.stream()
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    public Member(String loginId, String password, String nickname, String email, String phone) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }

    public Member(Long id, String accountId, AuthProvider authProvider, String loginId, String password, String nickname, String email, String phone) {
        this.id = id;
        this.oauth2 = new Oauth2(authProvider, accountId);
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }

    public boolean matchPassword(BCryptPasswordEncoder encoder, String password) {
        return encoder.matches(password, this.password);
    }
}
