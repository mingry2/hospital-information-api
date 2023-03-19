package com.mustache.bbs1.domain.dto.user;

import com.mustache.bbs1.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequestByForm {
	private String userName;

	private String password;

	public User toEntity(String password) {
		return User.builder()
				.userName(this.userName)
				.password(password)
				.build();
	}

}
