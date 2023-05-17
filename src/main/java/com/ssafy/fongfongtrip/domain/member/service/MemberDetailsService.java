package com.ssafy.fongfongtrip.domain.member.service;

import com.ssafy.fongfongtrip.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.fongfongtrip.domain.member.dto.request.MemberRegisterRequest;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    public Member signup(MemberRegisterRequest memberRegisterRequest) {
        return memberRepository.save(memberRegisterRequest.toMember(encoder));
    }

    public Member login(MemberLoginRequest memberLoginRequest) {
        Member member = findByLoginId(memberLoginRequest.loginId());
        if (member.matchPassword(encoder, memberLoginRequest.password())) {
            return member;
        }
        throw new EntityNotFoundException();
    }

    private Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).orElseThrow(EntityNotFoundException::new);
    }
}
