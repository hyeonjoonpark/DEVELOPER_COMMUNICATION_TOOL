package org.hyunjooon.communication_devtools.domain.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HeaderType {
    CONTENT_TYPE("Content-Type"),
    AUTHORIZATION("Authorization");

    private final String name;
}
