package com.ssafy.fongfongtrip.domain.chat.entity;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.global.audit.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Chat extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Chat(MessageType type, String message, ChatRoom chatRoom, Member member) {
        this.type = type;
        this.message = message;
        this.chatRoom = chatRoom;
        this.member = member;
    }
}
