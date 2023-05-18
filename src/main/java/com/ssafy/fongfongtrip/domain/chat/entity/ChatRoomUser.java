package com.ssafy.fongfongtrip.domain.chat.entity;


import com.ssafy.fongfongtrip.domain.member.entity.Member;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ChatRoomUser {

    @EmbeddedId
    private ChatRoomUserId id;

    public ChatRoomUser(ChatRoom chatRoom, Member member) {
        this.id = new ChatRoomUserId(chatRoom, member);
    }
}
