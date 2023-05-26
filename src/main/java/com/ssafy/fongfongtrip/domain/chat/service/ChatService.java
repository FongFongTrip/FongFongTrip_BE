package com.ssafy.fongfongtrip.domain.chat.service;

import com.ssafy.fongfongtrip.domain.chat.dto.request.ChatRequest;
import com.ssafy.fongfongtrip.domain.chat.entity.Chat;
import com.ssafy.fongfongtrip.domain.chat.repository.ChatRepository;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import com.ssafy.fongfongtrip.global.audit.BaseTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    @Transactional
    public Chat save(ChatRequest chatRequest) {
        return chatRepository.save(chatRequest.toChat(memberService.findById(chatRequest.memberId())));
    }

    public List<Chat> findByChatRoomId(Long chatRoomId) {
        return chatRepository.findByChatRoom(chatRoomId).stream()
                .sorted(Comparator.comparing(Chat::getCreated))
                .toList();
    }
}
