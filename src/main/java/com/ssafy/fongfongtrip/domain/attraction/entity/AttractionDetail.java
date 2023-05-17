package com.ssafy.fongfongtrip.domain.attraction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("Detail")
public class AttractionDetail extends AttractionInfo {

    private String cat1;
    private String cat2;
    private String cat3;
    private String createdTime;
    private String modifiedTime;
    private String booktour;
}
