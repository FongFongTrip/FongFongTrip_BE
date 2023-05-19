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
public class BoardLike {

    @EmbeddedId
    private BoardLikeId id;

    public BoardLike(Board board, Member member) {
        this.id = new BoardLikeId(board, member);
    }
}
