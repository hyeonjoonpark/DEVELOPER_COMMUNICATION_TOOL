package org.hyunjooon.communication_devtools.domain.account.user.presentation.dto.request;

import lombok.Builder;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Role;

import java.util.List;

@Builder
public record SignUpRequest(
        String userId,
        String email,
        String password,
        String phoneNumber,
        String profileDescription,
        List<String> interested,
        Role role
) {

}
