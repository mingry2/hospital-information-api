package com.mustache.bbs1.controller.ui;

import com.mustache.bbs1.domain.dto.user.UserJoinRequestByForm;
import com.mustache.bbs1.exception.AppException;
import com.mustache.bbs1.exception.ErrorCode;
import com.mustache.bbs1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final BCryptPasswordEncoder encoder;

	private final UserRepository userRepository;

	//회원가입 페이지
	@GetMapping(value = "/join")
	public String join() {
		return "users/join";
	}

	//회원가입
	@PostMapping("/join")
	public String join(UserJoinRequestByForm userJoinRequestByForm) {
		//userName 중복일 경우 예외발생
		userRepository.findByUserName(userJoinRequestByForm.getUserName())
				.ifPresent(user -> {
					throw new AppException(ErrorCode.DUPLICATED_USER_NAME,
							ErrorCode.DUPLICATED_USER_NAME.getMessage());
				});

		//password 암호화하여 저장
		userRepository.save(userJoinRequestByForm.toEntity(
				encoder.encode(userJoinRequestByForm.getPassword())));

		return "redirect:/";
	}

	//로그인 페이지
	@GetMapping(value = "/login")
	public String login() {
		return "users/login";
	}

}
