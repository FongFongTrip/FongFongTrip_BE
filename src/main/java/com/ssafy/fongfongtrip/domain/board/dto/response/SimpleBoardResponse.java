package com.ssafy.fongfongtrip.domain.board.dto.response;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SimpleBoardResponse(Long id,
                                  String title,
                                  String content,
                                  LocalDateTime created,
                                  Boolean isNotice,
                                  String memberNickname,
                                  Boolean isWriter,
                                  Boolean liked,
                                  Boolean marked) {

    public static SimpleBoardResponse of(Board board, Boolean isWriter, Boolean liked, Boolean marked) {
        return SimpleBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .created(board.getCreated())
                .isNotice(board.getIsNotice())
                .memberNickname(board.getMember().getNickname())
                .isWriter(isWriter)
                .liked(liked)
                .marked(marked)
                .build();
    }
}
