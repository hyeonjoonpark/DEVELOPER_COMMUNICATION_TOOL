package org.hyunjooon.communication_devtools.domain.room_user.repository;

import org.hyunjooon.communication_devtools.domain.room_user.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomUserRepository extends JpaRepository<RoomUser, Long> {
    
}
