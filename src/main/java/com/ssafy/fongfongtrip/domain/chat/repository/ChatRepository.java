package com.ssafy.fongfongtrip.domain.chat.repository;

import com.ssafy.fongfongtrip.domain.chat.entity.Chat;
import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ChatRepository extends CrudRepository<Chat, Long> {

    List<Chat> findByChatRoom(Long chatRoom);
}
