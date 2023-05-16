package com.ssafy.fongfongtrip.domain.attraction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
public class AttractionDetail {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private AttractionInfo attractionInfo;
    private String cat1;
    private String cat2;
    private String cat3;
    private String createdTime;
    private String modifiedTime;
    private String booktour;
}
