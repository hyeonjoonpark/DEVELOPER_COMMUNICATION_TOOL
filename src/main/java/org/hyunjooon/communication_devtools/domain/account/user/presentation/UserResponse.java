package org.hyunjooon.communication_devtools.domain.account.user.presentation;

import lombok.Builder;

@Builder
public record UserResponse(
        String userId,
        String email,
        String phoneNumber
) {

}
