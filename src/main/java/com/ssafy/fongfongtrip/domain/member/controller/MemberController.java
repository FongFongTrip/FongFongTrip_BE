package com.ssafy.fongfongtrip.domain.member.controller;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.config.security.jwt.JwtProvider;
import com.ssafy.fongfongtrip.config.security.oauth.mapper.LoginUserMapper;
import com.ssafy.fongfongtrip.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.fongfongtrip.domain.member.dto.request.MemberRegisterRequest;
import com.ssafy.fongfongtrip.domain.member.dto.response.AuthResponse;
import com.ssafy.fongfongtrip.domain.member.dto.response.MemberResponse;
import com.ssafy.fongfongtrip.domain.member.dto.response.SimpleMemberResponse;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.domain.member.service.MemberDetailsService;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberDetailsService memberDetailsService;
    private final JwtProvider jwtProvider;
    private final LoginUserMapper loginUserMapper;

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> meResponse(@AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(MemberResponse.of(loginUser.getMember()));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> memberDetails(@PathVariable Long memberId) {
        return ResponseEntity.ok(MemberResponse.of(memberService.findById(memberId)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Validated MemberLoginRequest memberLoginRequest) {
        return ResponseEntity.ok(AuthResponse.of(jwtProvider.createJwtToken(loginUserMapper.toLoginUser(memberDetailsService.login(memberLoginRequest)))));
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@RequestBody @Validated MemberRegisterRequest memberRegisterRequest) {
        return ResponseEntity.ok(MemberResponse.of(memberDetailsService.signup(memberRegisterRequest)));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Object> memberDelete(@PathVariable Long memberId) {
        memberService.deleteById(memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/memberList")
    public ResponseEntity<List<SimpleMemberResponse>> memberList(@PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Member> paging = memberService.findPaging(pageable);
        return ResponseEntity.ok(paging.stream()
                .map(SimpleMemberResponse::of)
                .collect(Collectors.toList()));
    }
}