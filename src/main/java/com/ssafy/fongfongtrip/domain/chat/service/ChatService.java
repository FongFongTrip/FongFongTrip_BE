package com.ssafy.fongfongtrip.domain.chat.service;

import com.ssafy.fongfongtrip.domain.chat.dto.request.ChatRequest;
import com.ssafy.fongfongtrip.domain.chat.entity.Chat;
import com.ssafy.fongfongtrip.domain.chat.repository.ChatRepository;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    public Chat save(ChatRequest chatRequest) {
        return chatRepository.save(chatRequest.toChat(
                chatRoomService.findById(chatRequest.roomId()),
                memberService.findById(chatRequest.memberId())));
    }
}
