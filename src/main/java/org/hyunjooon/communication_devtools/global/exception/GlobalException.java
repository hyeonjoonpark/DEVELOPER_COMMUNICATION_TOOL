package org.hyunjooon.communication_devtools.global.exception;

import lombok.Getter;
import org.hyunjooon.communication_devtools.global.exception.errorCode.ErrorCode;

@Getter
public class GlobalException extends Throwable{
    private final ErrorCode errorCode;

    public GlobalException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
