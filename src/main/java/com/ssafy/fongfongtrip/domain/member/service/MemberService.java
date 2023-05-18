package com.ssafy.fongfongtrip.domain.member.service;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public Page<Member> findPaging(Pageable pageable) {
        return memberRepository.findPaging(pageable);
    }
}
