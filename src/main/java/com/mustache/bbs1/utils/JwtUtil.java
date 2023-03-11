package com.mustache.bbs1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {

	public static String getUserName(String token, String secretKey){
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().get("userName", String.class);
	}

	public static boolean isExpired(String token, String secretKey){
		return Jwts.parser().setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()
				.before(new Date());
	}

//	// token을 열어서 내용을 확인
//	public static Claims openToken(String token, String secretKey) {
//		//secreteKey를 가지고 token의 정보를 꺼내 그 정보 중에 body를 get함
//		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//	}
//
//	//token 만료여부 확인
//	public static boolean isExpired(String token, String secretKey) {
//		//openToken을 통해 token을 열어 받아온 body에
//		//만료시간 Expiration을 가져와서 현재시간과 비교하여 현재 시간 이전인지 확인
//		return openToken(token, secretKey).getExpiration().before(new Date());
//	}

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
