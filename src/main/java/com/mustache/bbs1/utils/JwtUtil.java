package com.mustache.bbs1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.internal.lang.reflect.SignaturePatternImpl;

import java.util.Date;

@Slf4j
public class JwtUtil {

    // token을 열어서 내용을 확인
    public static Claims openToken(String token, String secretKey){
        // secreteKey를 가지고 token의 정보를 꺼내 그 정보 중에 body를 get함
        log.info("token이 잘 넘어오는지 :{}", token);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public static boolean isExpired(String token, String secretKey){
        // openToken을 통해 token을 열어 받아온 body에
        // 만료시간 Expiration을 가져와서 현재시간과 비교하여 현재 시간 이전인지 확인
        return openToken(token,secretKey).getExpiration().before(new Date());
    }

    public static String createToken(String secretKey){
        Claims claims = Jwts.claims();
        claims.put("userName", "mingyeong1");
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+3600000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
