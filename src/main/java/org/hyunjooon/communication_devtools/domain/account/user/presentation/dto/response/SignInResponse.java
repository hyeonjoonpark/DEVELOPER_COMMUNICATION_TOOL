package org.hyunjooon.communication_devtools.domain.account.user.presentation.dto.response;

public record SignInResponse(
        String userId,
        String email,
        String accessToken,
        String refreshToken
) {

}
