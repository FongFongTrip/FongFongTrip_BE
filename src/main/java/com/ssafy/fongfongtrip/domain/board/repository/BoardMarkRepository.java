package com.ssafy.fongfongtrip.domain.board.repository;

import com.ssafy.fongfongtrip.domain.board.entity.BoardMark;
import com.ssafy.fongfongtrip.domain.board.entity.BoardMarkId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardMarkRepository extends JpaRepository<BoardMark, BoardMarkId> {
    Boolean existsByIdBoardIdAndIdMemberId(Long boardId, Long memberId);

    void deleteByIdBoardIdAndIdMemberId(Long boardId, Long memberId);
}
