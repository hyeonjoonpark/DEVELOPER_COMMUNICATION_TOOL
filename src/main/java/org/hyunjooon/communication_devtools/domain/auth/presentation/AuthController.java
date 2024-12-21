package org.hyunjooon.communication_devtools.domain.auth.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.request.SignInRequest;
import org.hyunjooon.communication_devtools.domain.auth.presentation.dto.request.SignUpRequest;
import org.hyunjooon.communication_devtools.domain.auth.service.AuthService;
import org.hyunjooon.communication_devtools.global.common.GlobalResponse;
import org.hyunjooon.communication_devtools.global.exception.GlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public GlobalResponse<?> signUp(@RequestBody SignUpRequest request) throws GlobalException {
        return authService.create(request);
    }

    @PostMapping("/signIn")
    public GlobalResponse<?> signIn(@RequestBody SignInRequest request, HttpServletResponse response) throws GlobalException {
        return authService.signIn(request, response);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws GlobalException {
        return authService.logout(servletRequest, servletResponse);
    }
}
