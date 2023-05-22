package com.ssafy.fongfongtrip.domain.board.service;

import com.ssafy.fongfongtrip.domain.board.dto.request.BoardRegisterRequest;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardSearchRequest;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardUpdateRequest;
import com.ssafy.fongfongtrip.domain.board.entity.*;
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
    public Board save(BoardRegisterRequest boardRegisterRequest, Long memberId) {
        return boardRepository.save(boardRegisterRequest.toBoard(memberService.findById(memberId)));
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
    public Board boardUpdate(BoardUpdateRequest boardUpdateRequest, Long boardId) {
        boardRepository.findById(boardId)
                .ifPresentOrElse(board -> board.updateBoard(boardUpdateRequest.title(), boardUpdateRequest.content(), boardUpdateRequest.isNotice()),
                () -> { throw new EntityNotFoundException(); });
        return findById(boardId);
    }

    public Boolean liked(Long boardId, Long memberId) {
        return boardLikeRepository.existsById(new BoardLikeId(findById(boardId), memberService.findById(memberId)));
    }

    public Boolean marked(Long boardId, Long memberId) {
        return boardMarkRepository.existsById(new BoardMarkId(findById(boardId), memberService.findById(memberId)));
    }

    @Transactional
    public void like(Long boardId, Long memberId) {
        if (liked(boardId, memberId)) {
            return;
        }
        boardLikeRepository.save(new BoardLike(findById(boardId), memberService.findById(memberId)));
    }

    @Transactional
    public void unlike(Long boardId, Long memberId) {
        boardLikeRepository.deleteById(new BoardLikeId(findById(boardId), memberService.findById(memberId)));
    }

    @Transactional
    public void mark(Long boardId, Long memberId) {
        if (marked(boardId, memberId)) {
            return;
        }
        boardMarkRepository.save(new BoardMark(findById(boardId), memberService.findById(memberId)));
    }

    @Transactional
    public void unmark(Long boardId, Long memberId) {
        boardMarkRepository.deleteById(new BoardMarkId(findById(boardId), memberService.findById(memberId)));
    }

    public long getCount() {
        return boardRepository.count();
    }
}
