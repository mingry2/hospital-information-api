package com.mustache.bbs1.configuration;

import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.exception.ErrorCode;
import com.mustache.bbs1.service.UserService;
import com.mustache.bbs1.utils.JwtUtil;
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
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, RuntimeException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION); //authorization에 있는 정보 꺼내오기
        log.debug("authorization: {}", authorization);

        //authorization가 없거나 authorization의 형식이 안맞을 때
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            request.setAttribute("invalidTokenException", ErrorCode.INVALID_TOKEN); // customauthenticationentrypoint까지 넘어가서 에러코드반환하기
            //다음으로 넘어가지 말고 바로 필터 종료
            filterChain.doFilter(request, response);
            return;
        }

        //token 꺼내기
        String token = authorization.split(" ")[1];

        //token 만료여부 확인
        if (JwtUtil.isExpired(token, secretKey)){
            log.error("token 사용시간이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        //token에서 userName 꺼내기
        String userName = JwtUtil.getUserName(token, secretKey);
        log.debug("userName : {} ", userName);


        //문열어주기
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("User")));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 권한 부여

        filterChain.doFilter(request, response);

    }
}
