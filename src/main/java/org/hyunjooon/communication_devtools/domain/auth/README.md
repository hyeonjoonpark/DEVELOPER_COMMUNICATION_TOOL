# redis-cli

### redis-cli에서 refreshToken 값 확인하는 명령어

```redis
HGET refreshToken:{userName} refreshToken
```