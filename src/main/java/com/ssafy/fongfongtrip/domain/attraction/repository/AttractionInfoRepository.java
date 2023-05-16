package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer> {
}
