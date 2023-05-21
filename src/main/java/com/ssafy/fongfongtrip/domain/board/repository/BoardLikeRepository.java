package com.ssafy.fongfongtrip.domain.board.repository;

import com.ssafy.fongfongtrip.domain.board.entity.BoardLike;
import com.ssafy.fongfongtrip.domain.board.entity.BoardLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, BoardLikeId> {
}
