package com.ssafy.fongfongtrip.domain.chat.dto.response;

import com.ssafy.fongfongtrip.domain.chat.entity.Chat;
import com.ssafy.fongfongtrip.domain.chat.entity.MessageType;

import java.time.LocalDateTime;

public record ChatResponse(MessageType type,
                           String message,
                           String nickname,
                           LocalDateTime created,
                           Long roomId) {

    public static ChatResponse of(Chat chat) {
        return new ChatResponse(chat.getType(), chat.getMessage(),
                chat.getMember().getNickname(), chat.getCreated(), chat.getChatRoom().getId());
    }
}
