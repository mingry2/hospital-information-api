package com.mustache.bbs1.service;

import com.mustache.bbs1.domain.dto.user.UserJoinRequest;
import com.mustache.bbs1.domain.dto.user.UserJoinResponse;
import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.exception.AppException;
import com.mustache.bbs1.exception.ErrorCode;
import com.mustache.bbs1.repository.UserRepository;
import com.mustache.bbs1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder encoder;

	/**
	 * token 설정
	 **/
	@Value("${jwt.token.secret}")
	private String secretKey;

	private Long expiredTimeMs = 1 * 1000 * 60 * 60L * 24; // 하루
//	private Long expiredTimeMs = 1000 * 60 * 60L; // 1시간

	//회원가입
	public UserJoinResponse join(UserJoinRequest userJoinRequest) {
		//userName 중복일 경우 예외발생
		userRepository.findByUserName(userJoinRequest.getUserName())
				.ifPresent(user -> {
					throw new AppException(ErrorCode.DUPLICATED_USER_NAME,
							ErrorCode.DUPLICATED_USER_NAME.getMessage());
				});

		//password 암호화하여 저장
		User savedUser = userRepository.save(
				userJoinRequest.toEntity(encoder.encode(userJoinRequest.getPassword())));

		return UserJoinResponse.from(savedUser);

	}

	//로그인
	public String login(String userName, String password) {
		//userName이 존재하지 않으면 예외 발생
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND,
						ErrorCode.USERNAME_NOT_FOUND.getMessage()));

		//password 일치하지 않으면 예외 발생
		if (!encoder.matches(password, user.getPassword())) {
			throw new AppException(ErrorCode.INVALID_PASSWORD,
					ErrorCode.INVALID_PASSWORD.getMessage());
		}

		//로그인 성공 -> token 발급
		String token = JwtUtil.createToken(user.getUserName(), secretKey, expiredTimeMs);

		return token;
	}

}
