package org.hyunjooon.communication_devtools.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("사용자를 찾을 없습니다", 404);

    private final String message;
    private final int status;
}
