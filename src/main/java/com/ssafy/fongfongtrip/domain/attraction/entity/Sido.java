package com.ssafy.fongfongtrip.domain.attraction.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sido_code", nullable = false)
    private Integer sidoCode;
    private String sidoName;
}
