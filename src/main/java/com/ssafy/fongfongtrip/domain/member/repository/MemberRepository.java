package com.ssafy.fongfongtrip.domain.member.repository;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauth2AccountId(String accountId);

    Optional<Member> findByLoginId(String loginId);

    Boolean existsByLoginId(String loginId);

    @Query(value = "select m from Member m order by m.id desc ",
            countQuery = "select count(m) from Member m")
    Page<Member> findPaging(Pageable pageable);
}
