package com.ssafy.fongfongtrip.domain.board.entity;

import lombok.Getter;

@Getter
public enum SearchType {
    TITLE("title"),
    CONTENT("content"),
    MEMBER_NICKNAME("memberNickname");

    private final String value;

    private SearchType(String value) {
        this.value = value;
    }
}
