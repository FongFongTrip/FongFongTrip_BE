package com.ssafy.fongfongtrip.config.security.oauth.mapper;

import com.ssafy.fongfongtrip.config.security.oauth.OAuth2Request;

import java.util.Map;

public interface AttributeMapper {
	OAuth2Request mapToDto(Map<String, Object> attributes);

}
