package com.ssafy.fongfongtrip.domain.board.controller;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionLikeResponse;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionMarkResponse;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardRegisterRequest;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardSearchRequest;
import com.ssafy.fongfongtrip.domain.board.dto.request.BoardUpdateRequest;
import com.ssafy.fongfongtrip.domain.board.dto.response.BoardResponse;
import com.ssafy.fongfongtrip.domain.board.dto.response.SimpleBoardResponse;
import com.ssafy.fongfongtrip.domain.board.entity.Board;
import com.ssafy.fongfongtrip.domain.board.service.BoardService;
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

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<SimpleBoardResponse>> boardList(@PageableDefault(page = 0, size = 20, sort = "created", direction = Sort.Direction.DESC) Pageable pageable,
                                                               @AuthenticationPrincipal LoginUser loginUser) {
        Page<Board> paging = boardService.findPaging(pageable);
        return ResponseEntity.ok(paging.stream()
                .map(board -> SimpleBoardResponse.of(board, isWriter(board, loginUser),
                        boardService.liked(board.getId(), loginUser.getMember().getId()),
                        boardService.marked(board.getId(), loginUser.getMember().getId())))
                .toList());
    }

    @PostMapping("/search")
    public ResponseEntity<List<SimpleBoardResponse>> searchList(@RequestBody @Validated BoardSearchRequest boardSearchRequest,
                                                                @PageableDefault(page = 0, size = 20, sort = "created", direction = Sort.Direction.DESC) Pageable pageable,
                                                                @AuthenticationPrincipal LoginUser loginUser) {
        Page<Board> paging = boardService.findByKeyword(boardSearchRequest, pageable);
        return ResponseEntity.ok(paging.stream()
                .map(board -> SimpleBoardResponse.of(board, isWriter(board, loginUser),
                        boardService.liked(board.getId(), loginUser.getMember().getId()),
                        boardService.marked(board.getId(), loginUser.getMember().getId())))
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

    @GetMapping("/{boardId}/like")
    public ResponseEntity<AttractionLikeResponse> like(@PathVariable Long boardId,
                                                       @AuthenticationPrincipal LoginUser loginUser) {
        boardService.like(boardId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionLikeResponse(true));
    }

    @DeleteMapping("/{boardId}/unlike")
    public ResponseEntity<AttractionLikeResponse> unlike(@PathVariable Long boardId,
                                                         @AuthenticationPrincipal LoginUser loginUser) {
        boardService.unlike(boardId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionLikeResponse(false));
    }

    @GetMapping("/{boardId}/mark")
    public ResponseEntity<AttractionMarkResponse> mark(@PathVariable Long boardId,
                                                       @AuthenticationPrincipal LoginUser loginUser) {
        boardService.mark(boardId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionMarkResponse(true));
    }

    @DeleteMapping("/{boardId}/unmark")
    public ResponseEntity<AttractionMarkResponse> unmark(@PathVariable Long boardId,
                                                         @AuthenticationPrincipal LoginUser loginUser) {
        boardService.unmark(boardId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionMarkResponse(false));
    }

    private Boolean isWriter(Board board, LoginUser loginUser) {
        return board.getMember().getId() == loginUser.getMember().getId();
    }
}
