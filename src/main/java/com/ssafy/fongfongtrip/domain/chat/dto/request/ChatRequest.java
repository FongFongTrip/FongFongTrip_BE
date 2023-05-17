package com.ssafy.fongfongtrip.domain.chat.dto.request;

import com.ssafy.fongfongtrip.domain.chat.entity.Chat;
import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoom;
import com.ssafy.fongfongtrip.domain.chat.entity.MessageType;
import com.ssafy.fongfongtrip.domain.member.entity.Member;

public record ChatRequest(MessageType type,
                          String message,
                          Long roomId,
                          Long memberId,
                          String nickname) {

    public Chat toChat(ChatRoom chatRoom, Member member) {
        return new Chat(type, message, chatRoom, member);
    }

    public ChatRequest entering() {
        return new ChatRequest(type, nickname + "님이 입장했습니다.", roomId, memberId, nickname);
    }

    public ChatRequest exit() {
        return new ChatRequest(type, nickname + "님이 퇴장했습니다.", roomId, memberId, nickname);
    }
}
