package com.ssafy.fongfongtrip.domain.chat.controller;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.domain.chat.dto.request.ChatRoomCreateRequest;
import com.ssafy.fongfongtrip.domain.chat.dto.response.ChatRoomResponse;
import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoom;
import com.ssafy.fongfongtrip.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping
    public ResponseEntity<List<ChatRoomResponse>> room(@AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(chatRoomService.findAllRooms().stream()
                .map(chatRoom -> ChatRoomResponse.of(chatRoom, isCreator(chatRoom.getMember().getId(), loginUser)))
                .toList());
    }

    // 채팅방 생성
    @PostMapping
    public ResponseEntity<ChatRoomResponse> createRoom(@RequestBody @Validated ChatRoomCreateRequest chatRoomCreateRequest,
                                                       @AuthenticationPrincipal LoginUser loginUser) {
        ChatRoom chatRoom = chatRoomService.createRoom(chatRoomCreateRequest, loginUser.getMember().getId());
        return ResponseEntity.ok(ChatRoomResponse.of(chatRoom, true));
    }

    // 특정 채팅방 조회
    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoomResponse> roomInfo(@PathVariable Long roomId,
                                                     @AuthenticationPrincipal LoginUser loginUser) {
        ChatRoom chatRoom = chatRoomService.findById(roomId);
        return ResponseEntity.ok(ChatRoomResponse.of(chatRoom, isCreator(chatRoom.getMember().getId(), loginUser)));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Object> deleteRoom(@PathVariable Long roomId,
                                              @AuthenticationPrincipal LoginUser loginUser) {
        chatRoomService.deleteRoom(roomId, loginUser.getMember().getId());
        return ResponseEntity.ok().build();
    }

    private Boolean isCreator(Long creatorId, LoginUser loginUser) {
        return creatorId.equals(loginUser.getMember().getId());
    }
}
