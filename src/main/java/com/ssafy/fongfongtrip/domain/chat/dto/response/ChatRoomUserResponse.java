package com.ssafy.fongfongtrip.domain.chat.dto.response;

import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoomUser;

public record ChatRoomUserResponse(Long roomId,
                                   Long memberId,
                                   String memberNickname) {

    public static ChatRoomUserResponse of(ChatRoomUser chatRoomUser) {
        return new ChatRoomUserResponse(
                chatRoomUser.getId().getChatRoom().getId(),
                chatRoomUser.getId().getMember().getId(),
                chatRoomUser.getId().getMember().getNickname());
    }
}
