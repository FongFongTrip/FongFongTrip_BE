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
public class ChatRoom extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String roomName;

    @Column(nullable = false)
    private Boolean lock;

    private String password;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public ChatRoom(String roomName, Boolean lock, String password, Member member) {
        this.roomName = roomName;
        this.lock = lock;
        this.password = password;
        this.member = member;
    }
}
