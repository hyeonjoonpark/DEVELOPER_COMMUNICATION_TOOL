package org.hyunjooon.communication_devtools.domain.auth.presentation.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
        String userId,
        String email,
        String phoneNumber
) {

}
