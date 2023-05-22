package com.ssafy.fongfongtrip.domain.attraction.controller;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.domain.attraction.dto.request.AttractionInfoRequest;
import com.ssafy.fongfongtrip.domain.attraction.dto.request.LocationRequest;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionDescriptionResponse;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionInfoResponse;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionLikeResponse;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionMarkResponse;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionDescription;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import com.ssafy.fongfongtrip.domain.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping
    public ResponseEntity<List<AttractionInfoResponse>> attractionListByPaging(@PageableDefault(page = 0, size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable,
                                                                               @RequestParam Double latitude, @RequestParam Double longitude,
                                                                               @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(attractionService.findByPaging(pageable, latitude, longitude).stream()
                .map(attraction ->
                        AttractionInfoResponse.of(attraction,
                                getLiked(loginUser, attraction),
                                getMarked(loginUser, attraction)))
                .toList());
    }

    @GetMapping("/contents/{contentTypeId}")
    public ResponseEntity<List<AttractionInfoResponse>> attractionListPagingByContentTypeId(@PathVariable Integer contentTypeId,
                                                                                            @PageableDefault(page = 0, size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable,
                                                                                            @RequestParam Double latitude, @RequestParam Double longitude,
                                                                                            @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(attractionService.findPagingByContentTypeId(pageable, contentTypeId, latitude, longitude).stream()
                .map(attraction ->
                        AttractionInfoResponse.of(attraction,
                                getLiked(loginUser, attraction),
                                getMarked(loginUser, attraction)))
                .toList());
    }

    @GetMapping("/search/{contentTypeId}")
    public ResponseEntity<List<AttractionInfoResponse>> attractionListPagingByContentTypeIdAndKeyword(@PathVariable Integer contentTypeId,
                                                                                            @PageableDefault(page = 0, size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable,
                                                                                            @RequestParam String keyword,
                                                                                            @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(attractionService.findPagingByContentTypeIdAndKeyword(pageable, contentTypeId, keyword).stream()
                .map(attraction ->
                        AttractionInfoResponse.of(attraction,
                                getLiked(loginUser, attraction),
                                getMarked(loginUser, attraction)))
                .toList());
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<AttractionDescriptionResponse> attractionInfoDetails(@PathVariable Integer contentId,
                                                                               @AuthenticationPrincipal LoginUser loginUser) {
        AttractionDescription attractionDescription = attractionService.findByContentId(contentId);
        return ResponseEntity.ok(AttractionDescriptionResponse.of(
                attractionDescription,
                getLiked(loginUser, attractionDescription),
                getMarked(loginUser, attractionDescription)));
    }


    @PostMapping
    public ResponseEntity<List<AttractionInfoResponse>> attractionListByCode(@RequestBody @Validated AttractionInfoRequest attractioninfoRequest,
                                                                             @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(attractionService.findAllByCode(attractioninfoRequest).stream()
                .map(attraction ->
                        AttractionInfoResponse.of(attraction,
                                getLiked(loginUser, attraction),
                                getMarked(loginUser, attraction)))
                .toList());
    }

    @PostMapping("/location")
    public ResponseEntity<List<AttractionInfoResponse>> attractionListByPosition(@RequestBody @Validated LocationRequest locationRequest,
                                                                                 @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(attractionService.findAllByLocation(locationRequest).stream()
                .map(attraction ->
                        AttractionInfoResponse.of(attraction,
                                getLiked(loginUser, attraction),
                                getMarked(loginUser, attraction)))
                .toList());
    }

    @GetMapping("/{contentId}/like")
    public ResponseEntity<AttractionLikeResponse> like(@PathVariable Integer contentId,
                                                       @AuthenticationPrincipal LoginUser loginUser) {
        attractionService.like(contentId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionLikeResponse(true));
    }

    @DeleteMapping("/{contentId}/unlike")
    public ResponseEntity<AttractionLikeResponse> unlike(@PathVariable Integer contentId,
                                                    @AuthenticationPrincipal LoginUser loginUser) {
        attractionService.unlike(contentId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionLikeResponse(false));
    }

    @GetMapping("/{contentId}/mark")
    public ResponseEntity<AttractionMarkResponse> mark(@PathVariable Integer contentId,
                                                       @AuthenticationPrincipal LoginUser loginUser) {
        attractionService.mark(contentId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionMarkResponse(true));
    }

    @DeleteMapping("/{contentId}/unmark")
    public ResponseEntity<AttractionMarkResponse> unmark(@PathVariable Integer contentId,
                                                    @AuthenticationPrincipal LoginUser loginUser) {
        attractionService.unmark(contentId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionMarkResponse(false));
    }

    private Boolean getLiked(LoginUser loginUser, AttractionInfo attraction) {
        if (loginUser == null) {
            return false;
        }

        return attractionService.liked(attraction.getContentId(), loginUser.getMember().getId());
    }

    private Boolean getMarked(LoginUser loginUser, AttractionInfo attraction) {
        if (loginUser == null) {
            return false;
        }
        return attractionService.marked(attraction.getContentId(), loginUser.getMember().getId());
    }
}
