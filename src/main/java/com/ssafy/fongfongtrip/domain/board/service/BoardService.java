package com.ssafy.fongfongtrip.domain.board.service;

import com.ssafy.fongfongtrip.domain.board.dto.request.BoardRegisterRequest;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardUpdateRequest;
import com.ssafy.fongfongtrip.domain.board.entity.Board;
import com.ssafy.fongfongtrip.domain.board.repository.BoardRepository;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    public void save(BoardRegisterRequest boardRegisterRequest, Long memberId) {
        boardRepository.save(boardRegisterRequest.toBoard(memberService.findById(memberId)));
    }

    public Page<Board> findPaging(Pageable pageable) {
        return boardRepository.findPaging(pageable);
    }

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(Long boardId, Long memberId) {
        boardRepository.deleteBoardByIdAndMemberId(boardId, memberId);
    }

    public void boardUpdate(BoardUpdateRequest boardUpdateRequest, Long boardId) {
        boardRepository.findById(boardId)
                .ifPresentOrElse(board -> board.updateBoard(boardUpdateRequest.title(), boardUpdateRequest.content(), boardUpdateRequest.isNotice()),
                () -> { throw new EntityNotFoundException(); });

    }
}
