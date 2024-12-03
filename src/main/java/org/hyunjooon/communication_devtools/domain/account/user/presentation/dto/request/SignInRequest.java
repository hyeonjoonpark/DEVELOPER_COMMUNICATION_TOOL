package org.hyunjooon.communication_devtools.domain.account.user.presentation.dto.request;

import lombok.Builder;

@Builder
public record SignInRequest(
        String email,
        String password
) {

}
