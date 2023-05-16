package com.ssafy.fongfongtrip.domain.attraction.entity;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class AttractionLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public AttractionLike(AttractionInfo attractionInfo, Member member) {
        this.attractionInfo = attractionInfo;
        this.member = member;
    }
}