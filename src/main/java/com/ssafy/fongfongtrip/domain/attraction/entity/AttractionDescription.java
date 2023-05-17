package com.ssafy.fongfongtrip.domain.attraction.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("Description")
public class AttractionDescription extends AttractionInfo {

    private String homepage;
    private String overview;
    private String telname;
}
