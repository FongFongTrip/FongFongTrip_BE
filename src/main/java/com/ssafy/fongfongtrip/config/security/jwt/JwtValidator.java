package com.ssafy.fongfongtrip.config.security.jwt;

import java.security.Key;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.config.security.oauth.mapper.LoginUserMapper;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtValidator {
	private final Key key;
    private final MemberService memberService;
    private final LoginUserMapper loginUserMapper;

    public Authentication getAuthentication(String accessToken) throws NumberFormatException, EntityNotFoundException {
        Claims claims = getTokenClaims(accessToken);
        Member member = memberService.findById(Long.parseLong(claims.get("id", String.class)));
        LoginUser loginUser = loginUserMapper.toLoginUser(member);

        return new UsernamePasswordAuthenticationToken(loginUser, "", loginUser.getAuthorities());
    }
    
    public LoginUser getLoginUser(String token) throws NumberFormatException, EntityNotFoundException {
    	Claims claims = getTokenClaims(token);
        Member member = memberService.findById(Long.parseLong(claims.get("id", String.class)));
        return loginUserMapper.toLoginUser(member);
    }

    private Claims getTokenClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
