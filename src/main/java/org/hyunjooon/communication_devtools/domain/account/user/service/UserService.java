package org.hyunjooon.communication_devtools.domain.account.user.service;

import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Gender;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Role;
import org.hyunjooon.communication_devtools.domain.account.user.presentation.dto.request.SignUpRequest;
import org.hyunjooon.communication_devtools.domain.account.user.repository.UserRepository;
import org.hyunjooon.communication_devtools.global.common.GlobalResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public GlobalResponse create(
            String userId,
            String userName,
            String email,
            Gender gender,
            String password,
            Integer age,
            String phoneNumber,
            String profileDescription,
            List<String> interested,
            Role role
    ) {

        boolean isExist = userRepository.existsById(userId);
        if(isExist) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = User.builder()
                .userId(userId)
                .userName(userName)
                .email(email)
                .gender(gender)
                .password(password)
                .age(age)
                .phoneNumber(phoneNumber)
                .profileDescription(profileDescription)
                .interested(interested)
                .role(role)
                .build();
        userRepository.save(user);
        return GlobalResponse.builder()
                .message("회원가입이 성공적으로 완료되었습니다")
                .data(user)
                .build();
    }
}
