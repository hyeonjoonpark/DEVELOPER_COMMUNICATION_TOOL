package org.hyunjooon.communication_devtools.domain.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtConstants {
    AUTH_ID("auth_id"),
    TYPE("type"),
    EMPTY(""),
    PREFIX("prefix"),
    ROLE("role"),
    ACCESS_TOKEN("access_token"),
    REFRESH_TOKEN("refresh_token");

    private final String message;
}
