package org.hyunjooon.communication_devtools.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("사용자를 찾을 없습니다", 404),
    USER_ALREADY_EXIST("이미 존재하는 사용자입니다", 409),
    INVALID_TOKEN("유효하지 않은 토큰입니다", 498),
    ACCESS_DENIED("접근이 거부되었습니다", 403),
    WRONG_PASSWORD("잘못된 비밀번호입니다", 400),
    INTERNAL_SERVER_ERROR("서버에서 에러가 발생하였습니다", 500);

    private final String message;
    private final int status;
}
