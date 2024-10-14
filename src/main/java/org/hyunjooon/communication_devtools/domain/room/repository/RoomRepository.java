package org.hyunjooon.communication_devtools.domain.room.repository;

import org.hyunjooon.communication_devtools.domain.room.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<ChatRoom, UUID> {
    
}
