package com.ssafy.fongfongtrip.domain.chat.repository;

import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    void deleteByIdAndMemberId(Long id, Long memberId);
}
