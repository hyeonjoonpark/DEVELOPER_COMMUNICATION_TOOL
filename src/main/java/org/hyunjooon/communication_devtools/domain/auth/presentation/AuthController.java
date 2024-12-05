package org.hyunjooon.communication_devtools.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.request.SignInRequest;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.request.SignUpRequest;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.response.UserResponse;
import org.hyunjooon.communication_devtools.domain.account.user.repository.UserRepository;
import org.hyunjooon.communication_devtools.domain.auth.service.AuthService;
import org.hyunjooon.communication_devtools.global.common.GlobalResponse;
import org.hyunjooon.communication_devtools.global.exception.GlobalException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final AuthService authService;

    @QueryMapping
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(user -> UserResponse.builder()
                        .userId(user.getUserId())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    @MutationMapping(value = "signUp")
    public GlobalResponse<?> signUp(@Argument SignUpRequest request) throws GlobalException {
        return authService.create(request);
    }

    @MutationMapping(value = "signIn")
    public GlobalResponse<?> signIn(@Argument SignInRequest request) throws GlobalException {
        return authService.
                signIn(request);
    }
}
