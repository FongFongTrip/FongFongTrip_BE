package com.ssafy.fongfongtrip.domain.comment.service;

import com.ssafy.fongfongtrip.domain.board.service.BoardService;
import com.ssafy.fongfongtrip.domain.comment.dto.request.CommentRegisterRequest;
import com.ssafy.fongfongtrip.domain.comment.entity.Comment;
import com.ssafy.fongfongtrip.domain.comment.repository.CommentRepository;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardService boardService;
    private final MemberService memberService;

    public List<Comment> findCommentsByBoardId(Long boardId) {
        return commentRepository.findCommentsByBoardIdOrderByGroupNumAscCreatedAsc(boardId);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Comment save(Long boardId, Long memberId, CommentRegisterRequest commentRegisterRequest) {
        Comment comment = commentRepository.save(commentRegisterRequest.toComment(
                boardService.findById(boardId),
                memberService.findById(memberId),
                getParent(commentRegisterRequest)));
        comment.initParentCommentGroupNum(comment.getId());
        return comment;
    }

    private Comment getParent(CommentRegisterRequest commentRegisterRequest) {
        Comment parent = null;
        if (commentRegisterRequest.parentId() != null) {
            parent = commentRepository.findById(commentRegisterRequest.parentId()).orElse(null);
        }
        return parent;
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
