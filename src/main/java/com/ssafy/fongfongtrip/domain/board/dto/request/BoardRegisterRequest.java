package com.ssafy.fongfongtrip.domain.board.dto.request;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;

public record BoardRegisterRequest(@NotNull String title,
                                   @NotNull String content,
                                   @NotNull Boolean isNotice) {

    public Board toBoard(Member member) {
        return new Board(title, content, isNotice, member);
    }
}
