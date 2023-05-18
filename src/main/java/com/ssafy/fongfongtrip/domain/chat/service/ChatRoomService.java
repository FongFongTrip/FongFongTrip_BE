package com.ssafy.fongfongtrip.domain.chat.service;

import com.ssafy.fongfongtrip.domain.chat.dto.request.ChatRoomCreateRequest;
import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoom;
import com.ssafy.fongfongtrip.domain.chat.repository.ChatRoomRepository;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberService memberService;

    public List<ChatRoom> findAllRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findById(Long id) {
        return chatRoomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public ChatRoom createRoom(ChatRoomCreateRequest chatRoomCreateRequest, Long creatorId) {
        return chatRoomRepository.save(chatRoomCreateRequest.toChatRoom(memberService.findById(creatorId)));
    }

    @Transactional
    public void deleteRoom(Long roomId, Long memberId) {
        chatRoomRepository.deleteByIdAndMemberId(roomId, memberId);
    }
}
