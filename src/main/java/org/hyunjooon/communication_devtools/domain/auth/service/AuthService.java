package org.hyunjooon.communication_devtools.domain.auth.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.auth.RefreshToken;
import org.hyunjooon.communication_devtools.domain.auth.details.CustomUserDetails;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.request.SignInRequest;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.request.SignUpRequest;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.response.SignInResponse;
import org.hyunjooon.communication_devtools.domain.account.user.repository.UserRepository;
import org.hyunjooon.communication_devtools.domain.auth.details.CustomUserDetailService;
import org.hyunjooon.communication_devtools.domain.auth.repository.RefreshTokenRepository;
import org.hyunjooon.communication_devtools.global.common.GlobalResponse;
import org.hyunjooon.communication_devtools.global.exception.GlobalException;
import org.hyunjooon.communication_devtools.global.exception.errorCode.ErrorCode;
import org.hyunjooon.communication_devtools.global.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;

    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60L;
    private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 7L;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.secret}")
    private String JWT_SECRET_KEY;

    public GlobalResponse<?> create(SignUpRequest request) throws GlobalException {

        boolean isExist = userRepository.existsById(request.userId());
        if (isExist) {
            throw new GlobalException(ErrorCode.USER_ALREADY_EXIST);
        }

        User user = User.builder()
                .userId(request.userId())
                .userName(request.userName())
                .email(request.email())
                .gender(request.gender())
                .password(passwordEncoder.encode(request.password()))
                .age(request.age())
                .phoneNumber(request.phoneNumber())
                .profileDescription(request.profileDescription())
                .interested(request.interested())
                .role(request.role())
                .build();
        userRepository.save(user);
        return GlobalResponse.success("성공적으로 회원가입 되었습니다", user.getUserId(), HttpStatus.CREATED);
    }

    public GlobalResponse<?> signIn(SignInRequest signInRequest, HttpServletResponse servletResponse) throws GlobalException {
        CustomUserDetails user = (CustomUserDetails) customUserDetailService.loadUserByEmail(signInRequest.email());
        if (!user.isEnabled()) {
            throw new GlobalException(ErrorCode.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            throw new GlobalException(ErrorCode.WRONG_PASSWORD);
        }
        // AccessToken 발급
        String accessToken = jwtUtil.createToken(signInRequest.email(), ACCESS_TOKEN_EXPIRATION_TIME, JWT_SECRET_KEY);
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setMaxAge(Math.toIntExact(ACCESS_TOKEN_EXPIRATION_TIME));
        accessTokenCookie.setPath("/");
        servletResponse.addCookie(accessTokenCookie);

        // RefreshToken 발급
        String refreshToken = jwtUtil.createToken(signInRequest.email(), REFRESH_TOKEN_EXPIRATION_TIME, JWT_SECRET_KEY);
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setMaxAge(Math.toIntExact(REFRESH_TOKEN_EXPIRATION_TIME));
        refreshTokenCookie.setPath("/");
        servletResponse.addCookie(refreshTokenCookie);
        // Refresh token Redis 저장
//        redisTemplate.opsForValue().set("refresh_" + user.getUsername(), refreshToken, REFRESH_TOKEN_EXPIRATION_TIME, TimeUnit.MILLISECONDS);
        refreshTokenRepository.save(new RefreshToken(user.getUserId(), refreshToken));

        return GlobalResponse.success("성공적으로 로그인되었습니다",
                new SignInResponse(
                        user.getUsername(),
                        signInRequest.email()
                ), HttpStatus.OK
        );
    }

    public ResponseEntity<?> logout() {
        return null;
    }
}
