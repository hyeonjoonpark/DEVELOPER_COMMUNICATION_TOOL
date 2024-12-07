package org.hyunjooon.communication_devtools.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.request.SignInRequest;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.request.SignUpRequest;
import org.hyunjooon.communication_devtools.domain.auth.service.AuthService;
import org.hyunjooon.communication_devtools.global.common.GlobalResponse;
import org.hyunjooon.communication_devtools.global.exception.GlobalException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public GlobalResponse<?> signUp(@RequestBody SignUpRequest request) throws GlobalException {
        return authService.create(request);
    }

    @PostMapping(value = "signIn")
    public GlobalResponse<?> signIn(@RequestBody SignInRequest request) throws GlobalException {
        return authService.
                signIn(request);
    }
}
