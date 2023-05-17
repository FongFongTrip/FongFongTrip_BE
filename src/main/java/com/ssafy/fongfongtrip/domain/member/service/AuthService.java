package com.ssafy.fongfongtrip.domain.member.service;

import java.util.Optional;

import com.ssafy.fongfongtrip.config.security.oauth.OAuth2Request;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.domain.member.entity.Oauth2;
import com.ssafy.fongfongtrip.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final MemberRepository memberRepository;
	
	public Member saveIfNotExists(OAuth2Request oAuth2Request) {
		return memberRepository.findByOauth2AccountId(oAuth2Request.getAccountId())
				.orElseGet(() -> save(oAuth2Request));
	}

    private Member save(OAuth2Request oAuth2Request) {
        return memberRepository.save(new Member(oAuth2Request.getName(), new Oauth2(oAuth2Request.getAuthProvider(), oAuth2Request.getAccountId())));
    }
    

}
