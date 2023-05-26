package com.ssafy.fongfongtrip.domain.chat.entity;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.global.audit.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RedisHash(value = "chat", timeToLive = 1209600)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime lastUpdated;

    @Column(nullable = false)
    private String message;

    @Indexed
    private Long chatRoom;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_id")
//    @Indexed
//    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Chat(MessageType type, String message, Long chatRoom, Member member) {
        this.type = type;
        this.created = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.message = message;
        this.chatRoom = chatRoom;
        this.member = member;
    }
}
