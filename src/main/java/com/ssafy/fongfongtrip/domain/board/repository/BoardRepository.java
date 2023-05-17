package com.ssafy.fongfongtrip.domain.board.repository;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select b from Board b order by b.created desc ",
            countQuery = "select count(b) from Board b")
    Page<Board> findPaging(Pageable pageable);

    void deleteBoardByIdAndMemberId(Long id, Long memberId);
}
