package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.Plan;
import com.ssafy.fongfongtrip.domain.attraction.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("select r from Route r join fetch r.attractionInfo where r.plan = :plan")
    List<Route> findRoutesByPlan(@Param("plan") Plan plan);
}
