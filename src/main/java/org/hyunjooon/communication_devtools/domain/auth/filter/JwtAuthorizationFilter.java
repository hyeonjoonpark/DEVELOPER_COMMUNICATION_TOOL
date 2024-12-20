package org.hyunjooon.communication_devtools.domain.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.auth.enums.HeaderType;
import org.hyunjooon.communication_devtools.domain.auth.enums.TokenType;
import org.hyunjooon.communication_devtools.global.exception.errorCode.ErrorCode;
import org.hyunjooon.communication_devtools.global.utils.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil; // JWT 토큰을 검증하는 서비스

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String accessToken = request.getHeader(HeaderType.AUTHORIZATION.getDescription());

        if (accessToken != null && !accessToken.isBlank() && accessToken.startsWith(TokenType.BEARER.getDescription())) {
            String token = accessToken.substring(TokenType.BEARER.getDescription().length()).trim();

            if (jwtUtil.validateToken(token)) {
                Authentication authentication = jwtUtil.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorCode.INVALID_TOKEN.getMessage());
                return; // 인증 실패 시 더 이상 필터 체인을 진행하지 않음
            }
        }

        filterChain.doFilter(request, response);
    }
}
