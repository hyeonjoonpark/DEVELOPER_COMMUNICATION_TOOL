package org.hyunjooon.communication_devtools.domain.board_comment.repository;

import org.hyunjooon.communication_devtools.domain.board_comment.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    
}
