package org.hyunjooon.communication_devtools.domain.room;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity @Getter @Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
    @Id @Column(name = "chat_room_id") private UUID id;

    @Column(nullable = false, length = 20) private String roomTitle; // 방 제목
    @Column(length = 50) private String roomDescription; // 방 설명
    private String roomPassword; // 방 비밀번호

    // 방 생성한 Host(User) 엔티티 매핑

    @ElementCollection private List<String> topics; // 토픽
    @Column(nullable = false) @Builder.Default private boolean isPrivate = false; // 공개 여부 기본적으로 public 으로 지정됩니다

    @CreatedDate LocalDate createdDate;

    public ChatRoom(UUID id, String roomTitle, String roomDescription, String roomPassword, List<String> topics, boolean isPrivate, LocalDate createdDate) {
        this.id = id;
        this.roomTitle = roomTitle;
        this.roomDescription = roomDescription;
        this.roomPassword = roomPassword;
        this.topics = topics;
        this.isPrivate = isPrivate;
        this.createdDate = createdDate;
    }
}