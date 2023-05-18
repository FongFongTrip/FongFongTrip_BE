package com.ssafy.fongfongtrip.domain.board.controller;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardRegisterRequest;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardUpdateRequest;
import com.ssafy.fongfongtrip.domain.board.dto.response.BoardResponse;
import com.ssafy.fongfongtrip.domain.board.dto.response.SimpleBoardResponse;
import com.ssafy.fongfongtrip.domain.board.entity.Board;
import com.ssafy.fongfongtrip.domain.board.service.BoardService;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<SimpleBoardResponse>> boardList(@PageableDefault(page = 0, size = 20, sort = "created", direction = Sort.Direction.DESC) Pageable pageable,
                                                               @AuthenticationPrincipal LoginUser loginUser) {
        Page<Board> paging = boardService.findPaging(pageable);
        return ResponseEntity.ok(paging.stream()
                .map(board -> SimpleBoardResponse.of(board, isWriter(board, loginUser)))
                .toList());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse> boardDetails(@PathVariable Long boardId) throws EntityNotFoundException {
        return ResponseEntity.ok(BoardResponse.of(boardService.findById(boardId)));
    }

    @PostMapping
    public ResponseEntity<Objects> boardRegistry(@RequestBody @Validated BoardRegisterRequest boardRegisterRequest,
                                                 @AuthenticationPrincipal LoginUser loginUser) {
        boardService.save(boardRegisterRequest, loginUser.getMember().getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<Objects> boardUpdate(@PathVariable Long boardId, @RequestBody @Validated BoardUpdateRequest boardUpdateRequest) throws EntityNotFoundException {
        boardService.boardUpdate(boardUpdateRequest, boardId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Objects> boardDelete(@PathVariable Long boardId,
                                               @AuthenticationPrincipal LoginUser loginUser) throws EntityNotFoundException {
        boardService.deleteById(boardId, loginUser.getMember().getId());
        return ResponseEntity.ok().build();
    }

    private Boolean isWriter(Board board, LoginUser loginUser) {
        return board.getMember().getId() == loginUser.getMember().getId();
    }
}
