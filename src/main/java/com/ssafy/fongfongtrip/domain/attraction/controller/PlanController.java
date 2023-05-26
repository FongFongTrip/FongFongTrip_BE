package com.ssafy.fongfongtrip.domain.attraction.controller;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.domain.attraction.dto.request.PlanRequest;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionInfoResponse;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.PlanResponse;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.RouteResponse;
import com.ssafy.fongfongtrip.domain.attraction.entity.Plan;
import com.ssafy.fongfongtrip.domain.attraction.service.AttractionService;
import com.ssafy.fongfongtrip.domain.attraction.service.PlanService;
import com.ssafy.fongfongtrip.domain.member.dto.response.SimpleMemberResponse;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plans")
public class PlanController {
    private final PlanService planService;
    private final AttractionService attractionService;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<PlanResponse>> planList(Pageable pageable) {
        return ResponseEntity.ok(planService.findAllPagingPlans(pageable).stream()
                .map(plan -> new PlanResponse(plan.getId(), getRouteResponses(plan, plan.getMember().getId()), SimpleMemberResponse.of(plan.getMember())))
                .toList());
    }

    @GetMapping("/me")
    public ResponseEntity<List<PlanResponse>> planListByMemberId(@AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(planService.findPlansByMemberId(loginUser.getMember().getId()).stream()
                .map(plan -> new PlanResponse(plan.getId(), getRouteResponses(plan, loginUser.getMember().getId()), SimpleMemberResponse.of(memberService.findById(loginUser.getMember().getId()))))
                .toList());
    }

    private List<RouteResponse> getRouteResponses(Plan plan, Long memberId) {
        return planService.findRoutesByPlanId(plan).stream()
                .map(route -> RouteResponse.of(route, AttractionInfoResponse.of(route.getAttractionInfo(),
                            attractionService.liked(route.getAttractionInfo().getContentId(), memberId),
                            attractionService.marked(route.getAttractionInfo().getContentId(), memberId))))
                .toList();
    }

    @PostMapping
    public ResponseEntity<PlanResponse> save(@RequestBody @Validated PlanRequest planRequest,
                                             @AuthenticationPrincipal LoginUser loginUser) {
        Plan plan = planService.save(planRequest, loginUser.getMember().getId());

        return ResponseEntity.ok(new PlanResponse(plan.getId(), getRouteResponses(plan, loginUser.getMember().getId()), SimpleMemberResponse.of(memberService.findById(loginUser.getMember().getId()))));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Object> planDelete(@PathVariable Long planId) {
        planService.deletePlanByPlanId(planId);

        return ResponseEntity.ok().build();
    }
}
