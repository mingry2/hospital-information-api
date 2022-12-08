package com.mustache.bbs1.configuration;

import com.mustache.bbs1.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter { // 방명록 작성요청을 할 때마다 확인

    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // header에 있는 정보를 꺼내오기
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // header가 없거나 header의 형식이 안맞을 때
        if (header == null || !header.startsWith("Bearer ")) {
            // 다음으로 넘어가지 말고 바로 필터 종료
            filterChain.doFilter(request, response);
            return;
        }

        // token 꺼내기
        String token;
        try {
            token = header.split(" ")[1];
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        // token 만료여부 확인
        if (JwtUtil.isExpired(token, secretKey)){
            filterChain.doFilter(request, response);
            return;
        }

        String userName = JwtUtil.openToken(token, secretKey).get("userName").toString();

        // 문열어주기
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userName, null, List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 권한 부여
        filterChain.doFilter(request, response);



    }
}
