package com.ssafy.fongfongtrip.domain.board.dto.response;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardResponse(Long id,
                            String title,
                            String content,
                            LocalDateTime created,
                            LocalDateTime lastUpdated,
                            Boolean isNotice,
                            Boolean isWriter,
                            Boolean liked,
                            Boolean marked) {

    public static BoardResponse of(Board board,  Boolean isWriter, Boolean liked, Boolean marked) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .created(board.getCreated())
                .lastUpdated(board.getLastUpdated())
                .isNotice(board.getIsNotice())
                .isWriter(isWriter)
                .liked(liked)
                .marked(marked)
                .build();
    }
}
