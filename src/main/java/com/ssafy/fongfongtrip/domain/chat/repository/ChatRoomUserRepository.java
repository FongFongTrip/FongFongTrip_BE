package com.ssafy.fongfongtrip.domain.chat.repository;

import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoomUser;
import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoomUserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, ChatRoomUserId> {
    List<ChatRoomUser> findByIdChatRoomId(Long roomId);

    Optional<ChatRoomUser> findByIdChatRoomIdAndIdMemberId(Long roomId, Long memberId);
    void deleteByIdChatRoomIdAndIdMemberId(Long roomId, Long memberId);
}
