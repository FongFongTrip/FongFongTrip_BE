package com.ssafy.fongfongtrip.domain.member.repository;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauth2AccountId(String accountId);

    Optional<Member> findByLoginId(String loginId);
}
