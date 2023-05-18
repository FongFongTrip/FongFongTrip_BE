package com.ssafy.fongfongtrip.domain.chat.controller;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.domain.chat.dto.request.ChatRoomUserEnterRequest;
import com.ssafy.fongfongtrip.domain.chat.dto.request.ChatRoomUserExitRequest;
import com.ssafy.fongfongtrip.domain.chat.dto.response.ChatRoomUserResponse;
import com.ssafy.fongfongtrip.domain.chat.service.ChatRoomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/users")
@RequiredArgsConstructor
public class ChatRoomUserController {

    private final ChatRoomUserService chatRoomUserService;

    @GetMapping
    public ResponseEntity<List<ChatRoomUserResponse>> userList(@PathVariable Long roomId) {
        return ResponseEntity.ok(chatRoomUserService.findByRoomId(roomId).stream()
                .map(ChatRoomUserResponse::of)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ChatRoomUserResponse> enterRoomUser(@PathVariable Long roomId,
                                                              @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(ChatRoomUserResponse.of(chatRoomUserService.save(roomId, loginUser.getMember().getId())));
    }

    @DeleteMapping
    public ResponseEntity<Object> exitRoomUser(@PathVariable Long roomId,
                                               @AuthenticationPrincipal LoginUser loginUser) {
        chatRoomUserService.deleteByRoomIdAndMemberId(roomId, loginUser.getMember().getId());
        return ResponseEntity.ok().build();
    }
}
