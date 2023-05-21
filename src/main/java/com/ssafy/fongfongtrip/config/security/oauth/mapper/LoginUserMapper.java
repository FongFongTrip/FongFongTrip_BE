package com.ssafy.fongfongtrip.config.security.oauth.mapper;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class LoginUserMapper {
	
	public LoginUser toLoginUser(Member member) {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("id", member.getId());
        return new LoginUser(member, attributes, member.getAuthorityRole());
    }

}
