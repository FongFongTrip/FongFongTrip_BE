package com.ssafy.fongfongtrip.config.security.oauth;

import com.ssafy.fongfongtrip.config.security.oauth.mapper.AttributeMapperFactory;
import com.ssafy.fongfongtrip.config.security.oauth.mapper.AuthProvider;
import com.ssafy.fongfongtrip.config.security.oauth.mapper.LoginUserMapper;
import com.ssafy.fongfongtrip.domain.member.service.AuthService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DefaultUserService extends DefaultOAuth2UserService {
	
	private final AuthService authService;
    private final AttributeMapperFactory attributeMapperFactory;
    private final LoginUserMapper loginUserMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        AuthProvider authProvider = AuthProvider.valueOf(userRequest.getClientRegistration().getClientName().toUpperCase());
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2Request oAuth2Request = attributeMapperFactory.getAttributeMapper(authProvider)
                .mapToDto(oAuth2User.getAttributes());

        return loginUserMapper.toLoginUser(authService.saveIfNotExists(oAuth2Request));
    }
}
