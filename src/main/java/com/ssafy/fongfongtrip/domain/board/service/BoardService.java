package com.ssafy.fongfongtrip.domain.board.service;

import com.ssafy.fongfongtrip.domain.board.dto.request.BoardRegisterRequest;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardSearchRequest;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardUpdateRequest;
import com.ssafy.fongfongtrip.domain.board.entity.Board;
import com.ssafy.fongfongtrip.domain.board.entity.BoardLike;
import com.ssafy.fongfongtrip.domain.board.entity.BoardMark;
import com.ssafy.fongfongtrip.domain.board.entity.SearchType;
import com.ssafy.fongfongtrip.domain.board.repository.BoardLikeRepository;
import com.ssafy.fongfongtrip.domain.board.repository.BoardMarkRepository;
import com.ssafy.fongfongtrip.domain.board.repository.BoardRepository;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.fongfongtrip.domain.board.entity.SearchType.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardMarkRepository boardMarkRepository;
    private final MemberService memberService;

    @Transactional
    public void save(BoardRegisterRequest boardRegisterRequest, Long memberId) {
        boardRepository.save(boardRegisterRequest.toBoard(memberService.findById(memberId)));
    }

    public Page<Board> findPaging(Pageable pageable) {
        return boardRepository.findPaging(pageable);
    }

    public Page<Board> findByKeyword(BoardSearchRequest boardSearchRequest, Pageable pageable) {
        if(boardSearchRequest.category().equals(TITLE.getValue())){
            return boardRepository.findPagingByTitle(pageable, boardSearchRequest.keyword());
        }
        if(boardSearchRequest.category().equals(CONTENT.getValue())){
            return boardRepository.findPagingByContent(pageable, boardSearchRequest.keyword());
        }
        if(boardSearchRequest.category().equals(MEMBER_NICKNAME.getValue())){
            return boardRepository.findPagingByMemberId(pageable, boardSearchRequest.keyword());
        }
        throw new EntityNotFoundException();
    }

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void deleteById(Long boardId, Long memberId) {
        boardRepository.deleteBoardByIdAndMemberId(boardId, memberId);
    }

    @Transactional
    public void boardUpdate(BoardUpdateRequest boardUpdateRequest, Long boardId) {
        boardRepository.findById(boardId)
                .ifPresentOrElse(board -> board.updateBoard(boardUpdateRequest.title(), boardUpdateRequest.content(), boardUpdateRequest.isNotice()),
                () -> { throw new EntityNotFoundException(); });

    }

    public Boolean liked(Long boardId, Long memberId) {
        return boardLikeRepository.existsByIdBoardIdAndIdMemberId(boardId, memberId);
    }

    public Boolean marked(Long boardId, Long memberId) {
        return boardMarkRepository.existsByIdBoardIdAndIdMemberId(boardId, memberId);
    }

    public void like(Long boardId, Long memberId) {
        if (liked(boardId, memberId)) {
            return;
        }
        boardLikeRepository.save(new BoardLike(findById(boardId), memberService.findById(memberId)));
    }

    public void unlike(Long boardId, Long memberId) {
        boardLikeRepository.deleteByIdBoardIdAndIdMemberId(boardId, memberId);
    }

    public void mark(Long boardId, Long memberId) {
        if (marked(boardId, memberId)) {
            return;
        }
        boardMarkRepository.save(new BoardMark(findById(boardId), memberService.findById(memberId)));
    }

    public void unmark(Long boardId, Long memberId) {
        boardMarkRepository.deleteByIdBoardIdAndIdMemberId(boardId, memberId);
    }
}
