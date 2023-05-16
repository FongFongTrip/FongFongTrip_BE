package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionDescriptionRepository extends JpaRepository<AttractionDescription, Integer> {
}
