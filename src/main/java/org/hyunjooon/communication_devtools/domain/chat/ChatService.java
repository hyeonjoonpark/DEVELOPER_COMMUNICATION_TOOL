package org.hyunjooon.communication_devtools.domain.chat;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ChatService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("chat_topic", message);
    }
}
