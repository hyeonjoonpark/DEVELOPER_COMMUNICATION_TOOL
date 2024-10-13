package org.hyunjooon.communication_devtools.domain.account.user;

import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60) // TTL 일단 짧게 설정
public class RefreshToken {
    @Id
    private Long id;
    private String refreshToken;
    private Long expiration;
}
