package org.hyunjooon.communication_devtools.domain.account.user;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60) // TTL 일단 짧게 설정
public class RefreshToken {
    @Id
    private Long id;
    private final String accessToken;
    private final String refreshToken;

    public RefreshToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
