package com.ssafy.fongfongtrip.domain.comment.dto.request;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import com.ssafy.fongfongtrip.domain.comment.entity.Comment;
import com.ssafy.fongfongtrip.domain.member.entity.Member;

public record CommentRegisterRequest(String content,
                                     Long groupNum,
                                     Long parentId) {

    public Comment toComment(Board board, Member member, Comment parent) {
        return new Comment(content, groupNum, board, member, parent);
    }
}
