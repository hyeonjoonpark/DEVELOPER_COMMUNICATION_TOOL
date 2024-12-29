package org.hyunjooon.communication_devtools.domain.report.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Importance {
    VERY_IMPORTANT("매우 중요사항", 1),
    LITTLE_IMPORTANT("약간 중요함", 2),
    NOT_IMPORTANT("중요하지 않음", 3);

    private final String message;
    private final int level;
}
