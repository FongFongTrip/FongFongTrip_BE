package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findRoutesByPlanId(Long planId);
}
