package org.hyunjooon.communication_devtools.global.common;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record GlobalResponse<T>(
        String message,
        T data,
        HttpStatus httpStatus
) {
    public static <T> GlobalResponse<T> success(String message, T data, HttpStatus httpStatus) {
        return GlobalResponse.<T>builder()
                .message(message)
                .data(data)
                .httpStatus(httpStatus)
                .build();
    }

    public static <T> GlobalResponse<T> fail(String message, HttpStatus httpStatus) {
        return GlobalResponse.<T>builder()
                .message(message)
                .httpStatus(httpStatus)
                .build();
    }
}
