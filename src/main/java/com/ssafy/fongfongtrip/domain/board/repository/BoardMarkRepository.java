package com.ssafy.fongfongtrip.domain.board.repository;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import com.ssafy.fongfongtrip.domain.board.entity.BoardMark;
import com.ssafy.fongfongtrip.domain.board.entity.BoardMarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardMarkRepository extends JpaRepository<BoardMark, BoardMarkId> {

    @Query(value = "select b from Board b, BoardMark m where b.id = m.id.board.id and m.id.member.id = :memberId order by b.isNotice desc , b.created desc ",
            countQuery = "select count(b) from Board b")
    List<Board> findMarkByMemberId(@Param("memberId") Long memberId);
}
