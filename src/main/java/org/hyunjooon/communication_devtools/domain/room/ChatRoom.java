package org.hyunjooon.communication_devtools.domain.room;

import jakarta.persistence.*;
import lombok.*;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.room_user.RoomUser;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class ChatRoom {
    @Id
    @Column(name = "chat_room_id", nullable = false)
    private UUID id;

    @Column(nullable = false, length = 20) private String roomTitle; // 방 제목
    @Column(length = 50) private String roomDescription; // 방 설명
    private String roomPassword; // 방 비밀번호

    @ElementCollection private List<String> topics; // 토픽
    @Column(nullable = false)
    private boolean isPrivate = false; // 공개 여부 기본적으로 public 으로 지정됩니다

    @CreatedDate LocalDate createdDate;

    // 방 생성한 Host 사용자, User 엔티티 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Setter
    private User user;

    // 방에 접속한 User 엔티티 (RoomUser) 매핑
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomUser> roomUsers = new ArrayList<>();

    public void addRoomUser(RoomUser roomUser) {
        roomUser.setRoom(this);
        this.roomUsers.add(roomUser);
    }

    public ChatRoom(UUID id, String roomTitle, String roomDescription, String roomPassword, List<String> topics, boolean isPrivate, LocalDate createdDate, User user, List<RoomUser> roomUsers) {
        this.id = id;
        this.roomTitle = roomTitle;
        this.roomDescription = roomDescription;
        this.roomPassword = roomPassword;
        this.topics = topics;
        this.isPrivate = isPrivate;
        this.createdDate = createdDate;
        this.user = user;
        this.roomUsers = roomUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChatRoom chatRoom)) return false;
        return isPrivate == chatRoom.isPrivate && Objects.equals(id, chatRoom.id) && Objects.equals(roomTitle, chatRoom.roomTitle) && Objects.equals(roomDescription, chatRoom.roomDescription) && Objects.equals(roomPassword, chatRoom.roomPassword) && Objects.equals(topics, chatRoom.topics) && Objects.equals(createdDate, chatRoom.createdDate) && Objects.equals(user, chatRoom.user) && Objects.equals(roomUsers, chatRoom.roomUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomTitle, roomDescription, roomPassword, topics, isPrivate, createdDate, user, roomUsers);
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + id +
                ", roomTitle='" + roomTitle + '\'' +
                ", roomDescription='" + roomDescription + '\'' +
                ", roomPassword='" + roomPassword + '\'' +
                ", topics=" + topics +
                ", isPrivate=" + isPrivate +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", roomUsers=" + roomUsers +
                '}';
    }
}
