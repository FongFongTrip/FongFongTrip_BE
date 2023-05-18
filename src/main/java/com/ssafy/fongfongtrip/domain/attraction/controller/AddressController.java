package com.ssafy.fongfongtrip.domain.attraction.controller;

import com.ssafy.fongfongtrip.domain.attraction.dto.response.GugunResponse;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.SidoResponse;
import com.ssafy.fongfongtrip.domain.attraction.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/sido")
    public ResponseEntity<List<SidoResponse>> sidoList() {
        return ResponseEntity.ok(addressService.findAllSidos().stream()
                .map(SidoResponse::of)
                .toList());
    }

    @GetMapping("/sido/{sidoCode}/gugun")
    public ResponseEntity<List<GugunResponse>> gugunList(@PathVariable Integer sidoCode) {
        return ResponseEntity.ok(addressService.findGugunBySidoCode(sidoCode).stream()
                .map(GugunResponse::of)
                .toList());
    }
}
