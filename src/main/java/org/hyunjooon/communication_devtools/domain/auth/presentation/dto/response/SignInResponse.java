package org.hyunjooon.communication_devtools.domain.auth.presentation.dto.response;

public record SignInResponse(
        String userId,
        String email,
        String accessToken,
        String refreshToken
) {

}
