package com.ssafy.fongfongtrip.domain.chat.dto.request;

import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoom;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;

public record ChatRoomCreateRequest(@NotNull String roomName,
                                    @NotNull Boolean locked,
                                    String password) {

    public ChatRoom toChatRoom(Member member) {
        return new ChatRoom(roomName, locked, password, member);
    }
}
