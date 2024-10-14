package org.hyunjooon.communication_devtools.global.config;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public String sendMessage(String message) {
        chatService.sendMessage(message);
        return message;
    }
}
