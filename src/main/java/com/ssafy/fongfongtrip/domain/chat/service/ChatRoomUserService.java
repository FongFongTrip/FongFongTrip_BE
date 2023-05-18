package com.ssafy.fongfongtrip.domain.chat.service;

import com.ssafy.fongfongtrip.domain.chat.dto.request.ChatRoomUserEnterRequest;
import com.ssafy.fongfongtrip.domain.chat.dto.request.ChatRoomUserExitRequest;
import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoomUser;
import com.ssafy.fongfongtrip.domain.chat.repository.ChatRoomUserRepository;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomUserService {

    private final ChatRoomUserRepository chatRoomUserRepository;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    public List<ChatRoomUser> findByRoomId(Long roomId) {
        return chatRoomUserRepository.findByIdChatRoomId(roomId);
    }

    @Transactional
    public ChatRoomUser save(Long roomId, Long memberId) {
        return chatRoomUserRepository.findByIdChatRoomIdAndIdMemberId(roomId, memberId)
                .orElseGet(() -> chatRoomUserRepository.save(new ChatRoomUser(
                        chatRoomService.findById(roomId),
                        memberService.findById(memberId))));
    }

    @Transactional
    public void deleteByRoomIdAndMemberId(Long roomId, Long memberId) {
        chatRoomUserRepository.deleteByIdChatRoomIdAndIdMemberId(roomId, memberId);
    }
}
