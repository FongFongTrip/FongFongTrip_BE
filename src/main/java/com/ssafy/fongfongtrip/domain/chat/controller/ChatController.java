package com.ssafy.fongfongtrip.domain.chat.controller;

import com.ssafy.fongfongtrip.domain.chat.dto.request.ChatRequest;
import com.ssafy.fongfongtrip.domain.chat.dto.response.ChatResponse;
import com.ssafy.fongfongtrip.domain.chat.entity.MessageType;
import com.ssafy.fongfongtrip.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void chat(ChatRequest chatRequest) {
        if (chatRequest.type().equals(MessageType.ENTER)) {
            chatRequest = chatRequest.entering();
        } else if (chatRequest.type().equals(MessageType.EXIT)) {
            chatRequest = chatRequest.exit();
        }
        ChatResponse chatResponse = ChatResponse.of(chatService.save(chatRequest));
        sendingOperations.convertAndSend("/topic/chat/room/" + chatResponse.roomId(), chatResponse);
    }

    @GetMapping("/api/v1/chats")
    public ResponseEntity<List<ChatResponse>> chatList(@RequestParam Long roomId) {
        return ResponseEntity.ok(chatService.findByChatRoomId(roomId).stream()
                .map(ChatResponse::of)
                .toList());
    }
}
