package com.ssafy.fongfongtrip.domain.comment.dto.response;

import com.ssafy.fongfongtrip.domain.comment.entity.Comment;
import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Builder
public record CommentResponse(Long id,
                              String content,
                              LocalDateTime created,
                              LocalDateTime lastUpdated,
                              Long groupNum,
                              String writer,
                              Boolean isWriter,
                              String parentWriter) {

    public static CommentResponse of(Comment comment, Boolean isWriter) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .created(comment.getCreated())
                .lastUpdated(comment.getLastUpdated())
                .groupNum(comment.getGroupNum())
                .writer(comment.getMember().getNickname())
                .isWriter(isWriter)
                .parentWriter(getParentWriter(comment))
                .build();
    }

    private static String getParentWriter(Comment comment) {
        if (comment.getParent() == null) {
            return null;
        }
        return comment.getParent().getMember().getNickname();
    }
}
