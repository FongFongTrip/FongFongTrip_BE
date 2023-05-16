package com.ssafy.fongfongtrip.domain.attraction.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Sido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sido_code", nullable = false)
    private Integer sidoCode;
    private String sidoName;
}
