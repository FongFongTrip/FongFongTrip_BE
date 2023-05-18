package com.ssafy.fongfongtrip.domain.chat.dto.request;

import com.ssafy.fongfongtrip.domain.chat.entity.ChatRoomUser;
import com.ssafy.fongfongtrip.domain.member.entity.Member;

public record ChatRoomUserEnterRequest(Long roomId) {
}
