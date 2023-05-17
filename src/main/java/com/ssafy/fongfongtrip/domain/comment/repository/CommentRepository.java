package com.ssafy.fongfongtrip.domain.comment.repository;

import com.ssafy.fongfongtrip.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findCommentsByBoardId(Long boardId);
}
