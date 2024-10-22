package org.hyunjooon.communication_devtools.domain.account.user.presentation;

import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Gender;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Role;
import org.hyunjooon.communication_devtools.domain.account.user.repository.UserRepository;
import org.hyunjooon.communication_devtools.domain.account.user.service.UserService;
import org.hyunjooon.communication_devtools.global.common.GlobalResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @SchemaMapping(typeName = "Query", value = "allUsers")
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
    public GlobalResponse signUp(
            @Argument String userId,
            @Argument String userName,
            @Argument String email,
            @Argument Gender gender,
            @Argument String password,
            @Argument Integer age,
            @Argument String phoneNumber,
            @Argument String profileDescription,
            @Argument List<String> interested,
            @Argument Role role) {
        return userService.create(userId, userName, email, gender, password, age, phoneNumber, profileDescription, interested, role);
    }
}
