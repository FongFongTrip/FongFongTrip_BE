package com.ssafy.fongfongtrip.domain.chat.dto.response;

import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoom;
import lombok.Builder;

@Builder
public record ChatRoomResponse(Long id,
                               String roomName,
                               Boolean locked,
                               String password,
                               Boolean isCreator) {

    public static ChatRoomResponse of(ChatRoom chatRoom, Boolean isCreator) {
        return ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .roomName(chatRoom.getRoomName())
                .locked(chatRoom.getLocked())
                .password(chatRoom.getPassword())
                .isCreator(isCreator)
                .build();
    }
}
