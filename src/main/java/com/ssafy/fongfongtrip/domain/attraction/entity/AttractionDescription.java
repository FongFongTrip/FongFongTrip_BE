package com.ssafy.fongfongtrip.domain.attraction.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
public class AttractionDescription {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private AttractionInfo attractionInfo;
    private String homepage;
    private String overview;
    private String telname;
}
