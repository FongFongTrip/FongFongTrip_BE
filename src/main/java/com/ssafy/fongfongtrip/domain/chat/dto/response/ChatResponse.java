package com.ssafy.fongfongtrip.domain.chat.dto.response;

import com.ssafy.fongfongtrip.domain.chat.entity.Chat;
import com.ssafy.fongfongtrip.domain.chat.entity.MessageType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChatResponse(MessageType type,
                           String message,
                           String nickname,
                           LocalDateTime created,
                           Long roomId,
                           Long memberId) {

    public static ChatResponse of(Chat chat) {
        return ChatResponse.builder()
                .type(chat.getType())
                .message(chat.getMessage())
                .nickname(chat.getMember().getNickname())
                .created(chat.getCreated())
                .roomId(chat.getChatRoom())
                .memberId(chat.getMember().getId())
                .build();
    }
}
