package com.ssafy.fongfongtrip.domain.attraction.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Route {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long routeOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public Route(Long routeOrder, AttractionInfo attractionInfo, Plan plan) {
        this.routeOrder = routeOrder;
        this.attractionInfo = attractionInfo;
        this.plan = plan;
    }
}
