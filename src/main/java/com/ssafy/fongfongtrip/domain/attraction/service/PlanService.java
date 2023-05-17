package com.ssafy.fongfongtrip.domain.attraction.service;

import com.ssafy.fongfongtrip.domain.attraction.dto.request.PlanRequest;
import com.ssafy.fongfongtrip.domain.attraction.dto.request.RouteRequest;
import com.ssafy.fongfongtrip.domain.attraction.entity.Plan;
import com.ssafy.fongfongtrip.domain.attraction.entity.Route;
import com.ssafy.fongfongtrip.domain.attraction.repository.AttractionInfoRepository;
import com.ssafy.fongfongtrip.domain.attraction.repository.PlanRepository;
import com.ssafy.fongfongtrip.domain.attraction.repository.RouteRepository;
import com.ssafy.fongfongtrip.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final RouteRepository routeRepository;
    private final AttractionInfoRepository attractionInfoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Plan save(PlanRequest planRequest, Long memberId) {
        Plan plan = planRepository.save(new Plan(memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new)));

        saveRoutes(planRequest, plan);
        return plan;
    }

    private void saveRoutes(PlanRequest planRequest, Plan plan) {
        long order = 1;
        List<RouteRequest> routeRequests = planRequest.routeRequests();
        for (RouteRequest routeRequest : routeRequests) {
            routeRepository.save(new Route(order++, attractionInfoRepository.findById(routeRequest.contentId()).orElseThrow(EntityNotFoundException::new), plan));
        }
    }

    public List<Plan> findAllPlans() {
        return planRepository.findAll();
    }

    public List<Plan> findPlansByMemberId(Long memberId) {
        return planRepository.findByMemberId(memberId);
    }

    public List<Route> findRoutesByPlanId(Plan plan) {
        return routeRepository.findRoutesByPlan(plan);
    }

    @Transactional
    public void deletePlanByPlanId(Long planId) {
        planRepository.deletePlanById(planId);
    }
}
