package org.hyunjooon.communication_devtools.domain.room_user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.room.ChatRoom;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "room_user_id")
    private Long id;

    // Room 엔티티와 1 : N 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "room_id") @Setter
    private ChatRoom room;

    // User 엔티티와 1 : N 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Setter
    private User user;
}
