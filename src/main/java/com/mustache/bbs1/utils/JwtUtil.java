package com.mustache.bbs1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {

	public static String getUserName(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().get("userName", String.class);
	}

	public static boolean isExpired(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()
				.before(new Date());
	}

	//token 생성
	public static String createToken(String userName, String secretKey, Long expireTimeMs) {
		//claims를 map처럼 사용 -> userName을 저장해둘 수 있음
		Claims claims = Jwts.claims();
		claims.put("userName", userName);

		return Jwts.builder()
				.setClaims(claims) //저장 된 userName을 Claims에 넣는다
				.setIssuedAt(new Date(System.currentTimeMillis())) //생성시간
				.setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) //만료시간
				.signWith(SignatureAlgorithm.HS256, secretKey) //서명
				.compact();
	}
}
