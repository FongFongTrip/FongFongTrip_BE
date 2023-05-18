package com.ssafy.fongfongtrip.domain.comment.entity;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.global.audit.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Long groupNum;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    public Comment(String content, Long groupNum, Board board, Member member, Comment parent) {
        this.content = content;
        this.groupNum = groupNum;
        this.board = board;
        this.member = member;
        this.parent = parent;
    }

    public void initParentCommentGroupNum(Long groupNum) {
        if (this.groupNum == null) {
            this.groupNum = groupNum;
        }
    }
}
