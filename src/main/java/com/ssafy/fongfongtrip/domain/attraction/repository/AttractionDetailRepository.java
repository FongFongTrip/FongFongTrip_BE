package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionDetailRepository extends JpaRepository<AttractionDetail, Integer> {
}
