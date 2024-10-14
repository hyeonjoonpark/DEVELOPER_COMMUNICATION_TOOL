package org.hyunjooon.communication_devtools.domain.board.repository;

import org.hyunjooon.communication_devtools.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    
}
