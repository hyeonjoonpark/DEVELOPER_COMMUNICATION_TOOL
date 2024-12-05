package org.hyunjooon.communication_devtools.domain.auth.presentation.dto.request;

import lombok.Builder;

@Builder
public record SignInRequest(
        String email,
        String password
) {

}
