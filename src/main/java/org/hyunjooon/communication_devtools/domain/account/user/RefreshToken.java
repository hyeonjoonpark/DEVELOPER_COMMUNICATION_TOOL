package org.hyunjooon.communication_devtools.domain.account.user;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60) // TTL 일단 짧게 설정
public class RefreshToken {
    @Id
    private Long id;
    private final String userId;
    private final String refreshToken;

    public RefreshToken(String userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}
