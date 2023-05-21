package com.ssafy.fongfongtrip.config.security.oauth.mapper;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.EnumMap;

@Component
public class AttributeMapperFactory {
    private final Map<AuthProvider, AttributeMapper> mapperMap =
            new EnumMap<>(AuthProvider.class);
    private final KakaoAttributeMapper kakaoAttributeMapper;
    private final GoogleAttributeMapper googleAttributeMapper;

    public AttributeMapperFactory(KakaoAttributeMapper kakaoAttributeMapper, GoogleAttributeMapper googleAttributeMapper) {
        this.kakaoAttributeMapper = kakaoAttributeMapper;
        this.googleAttributeMapper = googleAttributeMapper;
        initialize();
    }

    private void initialize() {
        mapperMap.put(AuthProvider.KAKAO, kakaoAttributeMapper);
        mapperMap.put(AuthProvider.GOOGLE, googleAttributeMapper);
    }

    public AttributeMapper getAttributeMapper(AuthProvider authProvider) {
        return mapperMap.get(authProvider);
    }
}
