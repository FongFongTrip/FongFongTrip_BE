package com.ssafy.fongfongtrip.global.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseTime {

    @CreatedDate
    @Column(columnDefinition = "datetime", updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(columnDefinition = "datetime")
    private LocalDateTime lastUpdated;
}
