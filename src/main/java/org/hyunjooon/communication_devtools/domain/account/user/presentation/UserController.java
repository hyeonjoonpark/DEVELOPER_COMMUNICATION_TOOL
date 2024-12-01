package org.hyunjooon.communication_devtools.domain.account.user.presentation;

import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.account.user.presentation.dto.request.SignUpRequest;
import org.hyunjooon.communication_devtools.domain.account.user.presentation.dto.response.UserResponse;
import org.hyunjooon.communication_devtools.domain.account.user.repository.UserRepository;
import org.hyunjooon.communication_devtools.domain.account.user.service.UserService;
import org.hyunjooon.communication_devtools.global.common.GlobalResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

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
    public GlobalResponse<?> signUp(@Argument SignUpRequest request) {
        return userService.create(request);
    }
}
