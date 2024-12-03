package org.hyunjooon.communication_devtools.domain.account.user.service;

import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.account.user.presentation.dto.request.SignInRequest;
import org.hyunjooon.communication_devtools.domain.account.user.presentation.dto.request.SignUpRequest;
import org.hyunjooon.communication_devtools.domain.account.user.repository.UserRepository;
import org.hyunjooon.communication_devtools.domain.auth.details.CustomUserDetailService;
import org.hyunjooon.communication_devtools.domain.auth.details.CustomUserDetails;
import org.hyunjooon.communication_devtools.global.common.GlobalResponse;
import org.hyunjooon.communication_devtools.global.exception.GlobalException;
import org.hyunjooon.communication_devtools.global.exception.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailService customUserDetailService;

    public GlobalResponse<?> create(SignUpRequest request) {

        boolean isExist = userRepository.existsById(request.userId());
        if(isExist) {
            throw new RuntimeException("이미 존재하는 사용자입니다");
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
        return GlobalResponse.success("성공적으로 회원가입 되었습니다", user, HttpStatus.CREATED);
    }

    public GlobalResponse<?> signIn(SignInRequest signInRequest) throws GlobalException {
        UserDetails user = customUserDetailService.loadUserByEmail(signInRequest.email());
        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            throw new GlobalException(ErrorCode.WRONG_PASSWORD);
        }

        // create JWT
        return null;
    }
}
