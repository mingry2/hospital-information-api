package com.mustache.bbs1.domain.dto.user;

import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.domain.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserJoinRequest {
	private String userName;
	private String password;

	public User toEntity(String encodedPassword) {
		return User.builder()
				.userName(this.userName)
				.password(encodedPassword)
				.build();
	}
}
