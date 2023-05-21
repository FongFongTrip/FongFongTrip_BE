package com.ssafy.fongfongtrip.domain.board.dto.response;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SimpleBoardResponse(Long id,
                                  String title,
                                  LocalDateTime created,
                                  Boolean isNotice,
                                  String memberNickname) {

    public static SimpleBoardResponse of(Board board) {
        return SimpleBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .created(board.getCreated())
                .isNotice(board.getIsNotice())
                .memberNickname(board.getMember().getNickname())
                .build();
    }
}
