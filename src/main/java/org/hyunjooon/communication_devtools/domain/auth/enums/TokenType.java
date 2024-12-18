package org.hyunjooon.communication_devtools.domain.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    BEARER("Bearer "),
    ACCESS("access"),
    REFRESH("refresh");

    private final String description;
}
