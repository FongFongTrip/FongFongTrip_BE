package com.ssafy.fongfongtrip.domain.attraction.service;

import com.ssafy.fongfongtrip.domain.attraction.entity.Gugun;
import com.ssafy.fongfongtrip.domain.attraction.entity.Sido;
import com.ssafy.fongfongtrip.domain.attraction.repository.GugunRepository;
import com.ssafy.fongfongtrip.domain.attraction.repository.SidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    public List<Sido> findAllSidos() {
        return sidoRepository.findAll();
    }

    public List<Gugun> findGugunBySidoCode(Integer sidoCode) {
        return gugunRepository.findGugunsBySido(sidoRepository.findById(sidoCode).orElseThrow(EntityNotFoundException::new));
    }
}
