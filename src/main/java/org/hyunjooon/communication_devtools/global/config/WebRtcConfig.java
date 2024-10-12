package org.hyunjooon.communication_devtools.global.config;

import org.hyunjooon.communication_devtools.global.handler.SignalingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebRtcConfig implements WebSocketConfigurer {
    // signal로 요청이 왔을 때 signalingSocketHandler가 동작하도록 registry에 설정
    // 요청은 클라이언트 접속, close, 메세지 발송 등에 대해 특정 메소드를 호출한다
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(signalingSocketHandler(), "/signal")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    // signalingSocketHandler
    @Bean
    public WebSocketHandler signalingSocketHandler() {
        return new SignalingHandler();
    }
}
