package com.ssafy.fongfongtrip.domain.attraction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
public class Gugun {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gugun_code", nullable = false)
    private Integer gugunCode;
    private String gugunName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;
}
