package com.ssafy.fongfongtrip.domain.attraction.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gugun {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gugun_code", nullable = false)
    private Integer gugunCode;
    private String gugunName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;
}
