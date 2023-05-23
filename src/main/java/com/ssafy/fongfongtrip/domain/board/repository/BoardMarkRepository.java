package com.ssafy.fongfongtrip.domain.board.repository;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import com.ssafy.fongfongtrip.domain.board.entity.BoardMark;
import com.ssafy.fongfongtrip.domain.board.entity.BoardMarkId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardMarkRepository extends JpaRepository<BoardMark, BoardMarkId> {

    @Query(value = "select b from Board b, BoardMark m where b.id = m.id.board.id and m.id.member.id = :memberId ",
            countQuery = "select count(b) from Board b")
    List<Board> findMarkByMemberId(@Param("memberId") Long memberId);
}
