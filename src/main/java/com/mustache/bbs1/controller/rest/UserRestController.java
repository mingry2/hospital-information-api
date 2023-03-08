package com.mustache.bbs1.controller.rest;

import com.mustache.bbs1.domain.Response;
import com.mustache.bbs1.domain.dto.user.UserJoinRequest;
import com.mustache.bbs1.domain.dto.user.UserJoinResponse;
import com.mustache.bbs1.domain.dto.user.UserLoginRequest;
import com.mustache.bbs1.domain.dto.user.UserLoginResponse;
import com.mustache.bbs1.repository.UserRepository;
import com.mustache.bbs1.service.UserService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

	private final UserService userService;
	private final UserRepository userRepository;

	@PostMapping(value = "join")
	public ResponseEntity<Response<UserJoinResponse>> join(@RequestBody UserJoinRequest userJoinRequest) {
		UserJoinResponse userJoinResponse = userService.join(userJoinRequest);

		return ResponseEntity.created(URI.create("api/v1/users" + userJoinResponse.getId())).body(
				Response.success(userJoinResponse));
	}

	@PostMapping(value = "login")
	public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
		String token = userService.login(userLoginRequest.getUserName(),
				userLoginRequest.getPassword());

		return Response.success(new UserLoginResponse(token));
	}

}
