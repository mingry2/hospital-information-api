package com.mustache.bbs1.configuration;

import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.service.UserService;
import com.mustache.bbs1.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;

    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION); //header에 있는 정보 꺼내오기

        //header가 없거나 header의 형식이 안맞을 때
        if (header == null || !header.startsWith("Bearer ")) {
            //다음으로 넘어가지 말고 바로 필터 종료
            filterChain.doFilter(request, response);
            return;
        }

        //token 꺼내기
        String token;
        try {
            token = header.split(" ")[1];
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        //token 만료여부 확인
        if (JwtTokenUtil.isExpired(token, secretKey)){
            filterChain.doFilter(request, response);
            return;
        }

        String userName = JwtTokenUtil.openToken(token, secretKey).get("userName").toString();

        //userDetail 설정
        User user = userService.getUserByUserName(userName);
        log.debug("userRole: {}", user.getRole());

        //문열어주기
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority(user.getRole().name())));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 권한 부여

        filterChain.doFilter(request, response);

    }
}
