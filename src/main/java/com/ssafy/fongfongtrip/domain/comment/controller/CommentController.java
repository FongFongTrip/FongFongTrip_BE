package com.ssafy.fongfongtrip.domain.comment.controller;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.domain.comment.dto.request.CommentRegisterRequest;
import com.ssafy.fongfongtrip.domain.comment.dto.response.CommentResponse;
import com.ssafy.fongfongtrip.domain.comment.entity.Comment;
import com.ssafy.fongfongtrip.domain.comment.service.CommentService;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/boards/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> CommentList(@PathVariable Long boardId,
                                                             @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(commentService.findCommentsByBoardId(boardId).stream()
                .map(comment -> CommentResponse.of(comment, isWriter(comment.getMember().getId(), loginUser)))
                .toList());
    }

    @PostMapping
    public ResponseEntity<CommentResponse> commentAdd(@PathVariable Long boardId,
                                              @RequestBody @Validated CommentRegisterRequest commentRegisterRequest,
                                              @AuthenticationPrincipal LoginUser loginUser) {
        Comment comment = commentService.save(boardId, loginUser.getMember().getId(), commentRegisterRequest);
        return ResponseEntity.ok(CommentResponse.of(comment, isWriter(comment.getMember().getId(), loginUser)));
    }

    private Boolean isWriter(Long id, LoginUser loginUser) {
        return id.equals(loginUser.getMember().getId());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Object> commentDelete(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.ok().build();
    }
}
