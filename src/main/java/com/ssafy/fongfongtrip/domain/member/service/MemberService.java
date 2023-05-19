package com.ssafy.fongfongtrip.domain.member.service;

import com.ssafy.fongfongtrip.domain.member.dto.request.SignupCheckRequest;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.domain.member.repository.MemberRepository;
import com.ssafy.fongfongtrip.global.exception.LoginIdDuplicationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Boolean checkLoginIdDuplication(SignupCheckRequest signupCheckRequest) {
        if (memberRepository.existsByLoginId(signupCheckRequest.loginId())) {
            throw new LoginIdDuplicationException();
        }
        return true;
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            return;
        }
        memberRepository.delete(member.get());
    }

    public Page<Member> findPaging(Pageable pageable) {
        return memberRepository.findPaging(pageable);
    }
}
