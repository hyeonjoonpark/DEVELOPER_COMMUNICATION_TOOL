package org.hyunjooon.communication_devtools.domain.auth.oauth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Registration {
    NAVER("naver"),
    KAKAO("kakao"),
    GITHUB("github"),
    GOOGLE("google"),
    FACEBOOK("facebook");

    private String id;
}
