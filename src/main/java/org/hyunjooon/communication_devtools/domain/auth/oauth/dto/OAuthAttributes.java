package org.hyunjooon.communication_devtools.domain.auth.oauth.dto;

import lombok.Builder;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Role;

import java.util.Map;

@Builder
public record OAuthAttributes(
        Map<String, Object> attributes,
        String nameAttributeKey,
        String name,
        String email,
        String picture
) {
    public static OAuthAttributes of(
            String registrationId,
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        switch (registrationId) {
            case "github" -> {
                return ofGithub(userNameAttributeName, attributes);
            }
            case "google" -> throw new RuntimeException("아직 구글 로그인 구현 안함");
            default -> throw new IllegalArgumentException("찾을 수 없는 로그인 방식입니다");
        }
    }
    private static OAuthAttributes ofGithub(
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        return OAuthAttributes.builder()
                .name(String.valueOf(attributes.get("login")))
                .email(String.valueOf(attributes.get("email")))
                .picture(String.valueOf(attributes.get("avatar_url")))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .userName(name)
                .email(email)
                .profileImageName(picture)
                .role(Role.ROLE_USER)
                .build();
    }

}
