package org.hyunjooon.communication_devtools.domain.account.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_USER("일반 사용자"),
    ROLE_ADMIN("관리자"),
    ROLE_GUEST("게스트");

    private final String name;

    public static Role of(String name) {
        return Arrays.stream(Role.values())
                .filter(role -> role.getName().equals(name))
                .findAny().orElse(ROLE_GUEST);
    }
}
