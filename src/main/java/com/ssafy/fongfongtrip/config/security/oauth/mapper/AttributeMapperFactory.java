package com.ssafy.fongfongtrip.config.security.oauth.mapper;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.EnumMap;

@Component
public class AttributeMapperFactory {
	private final Map<AuthProvider, AttributeMapper> mapperMap =
            new EnumMap<>(AuthProvider.class);
    private final KakaoAttributeMapper kakaoAttributeMapper;

    public AttributeMapperFactory(KakaoAttributeMapper kakaoAttributeMapper) {
        this.kakaoAttributeMapper = kakaoAttributeMapper;
        initialize();
    }

    private void initialize() {
        mapperMap.put(AuthProvider.KAKAO, kakaoAttributeMapper);
    }

    public AttributeMapper getAttributeMapper(AuthProvider authProvider) {
        return mapperMap.get(authProvider);
    }
}
