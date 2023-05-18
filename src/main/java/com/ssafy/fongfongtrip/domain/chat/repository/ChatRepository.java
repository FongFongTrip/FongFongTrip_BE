package com.ssafy.fongfongtrip.domain.chat.repository;

import com.ssafy.fongfongtrip.domain.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByChatRoomIdOrderByCreatedAsc(Long chatRoomId);
}
