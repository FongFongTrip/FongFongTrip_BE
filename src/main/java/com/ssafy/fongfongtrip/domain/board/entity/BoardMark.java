package com.ssafy.fongfongtrip.domain.board.entity;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardMark {

    @EmbeddedId
    private BoardMarkId id;

    public BoardMark(Board board, Member member) {
        this.id = new BoardMarkId(board, member);
    }
}
