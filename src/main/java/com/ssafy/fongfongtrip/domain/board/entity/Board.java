package com.ssafy.fongfongtrip.domain.board.entity;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.global.audit.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Boolean isNotice;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Board(String title, String content, Boolean isNotice, Member member) {
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
        this.member = member;
    }

    public void updateBoard(String title, String content, Boolean isNotice) {
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
    }
}
