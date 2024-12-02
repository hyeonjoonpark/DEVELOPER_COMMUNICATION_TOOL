package org.hyunjooon.communication_devtools.domain.account.user;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60) // TTL 일단 짧게 설정
public class RefreshToken {
    @Id
    private Long id;
    private final String refreshToken;
    private final Long expiration;

    public RefreshToken(Long id, String refreshToken, Long expiration) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
