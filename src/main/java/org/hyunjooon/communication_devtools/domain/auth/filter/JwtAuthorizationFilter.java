package org.hyunjooon.communication_devtools.domain.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.auth.enums.HeaderType;
import org.hyunjooon.communication_devtools.domain.auth.enums.TokenType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String accessToken = request.getHeader(HeaderType.AUTHORIZATION.getDescription());
        if (accessToken.isBlank() || !accessToken.startsWith(TokenType.BEARER.getDescription())) {
            filterChain.doFilter(request, response);
        }
    }
}
