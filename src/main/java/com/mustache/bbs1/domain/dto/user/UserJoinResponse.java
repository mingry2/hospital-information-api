package com.mustache.bbs1.domain.dto.user;

import com.mustache.bbs1.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserJoinResponse {

	private Long id;
	private String userName;

	public static UserJoinResponse from(User savedUser) {
		return UserJoinResponse.builder()
				.id(savedUser.getId())
				.userName(savedUser.getUserName())
				.build();
	}
}
