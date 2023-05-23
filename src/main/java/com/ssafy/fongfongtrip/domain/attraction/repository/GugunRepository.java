package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.Gugun;
import com.ssafy.fongfongtrip.domain.attraction.entity.Sido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GugunRepository extends JpaRepository<Gugun, Integer> {

    List<Gugun> findGugunsBySido(Sido sido);

    Optional<Gugun> findGugunsBySidoSidoCodeAndGugunCode(Integer sidoCode, Integer gugunCode);
}
