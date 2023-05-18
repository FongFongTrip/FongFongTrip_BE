package com.ssafy.fongfongtrip.domain.board.repository;

import com.ssafy.fongfongtrip.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select b from Board b order by b.isNotice desc , b.created desc ",
            countQuery = "select count(b) from Board b")
    Page<Board> findPaging(Pageable pageable);

    @Query(value = "select b from Board b where b.title like concat('%', :keyword, '%') order by b.isNotice desc , b.created desc ",
            countQuery = "select count(b) from Board b")
    Page<Board> findPagingByTitle(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "select b from Board b where b.content like concat('%', :keyword, '%') order by b.isNotice desc , b.created desc ",
            countQuery = "select count(b) from Board b")
    Page<Board> findPagingByContent(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "select b from Board b where b.member.nickname like concat('%', :keyword, '%') order by b.isNotice desc , b.created desc ",
            countQuery = "select count(b) from Board b")
    Page<Board> findPagingByMemberId(Pageable pageable, @Param("keyword") String keyword);

    void deleteBoardByIdAndMemberId(Long id, Long memberId);
}
