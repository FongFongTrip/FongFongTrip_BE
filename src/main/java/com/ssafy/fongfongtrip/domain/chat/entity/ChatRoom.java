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

    @Column(nullable = false, length = 30)
    private String roomName;

    @Column(nullable = false)
    private Boolean locked;

    @Column(length = 30)
    private String password;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public ChatRoom(String roomName, Boolean locked, String password, Member member) {
        this.roomName = roomName;
        this.locked = locked;
        this.password = password;
        this.member = member;
    }
}
