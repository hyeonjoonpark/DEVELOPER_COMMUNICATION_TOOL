package org.hyunjooon.communication_devtools.domain.post_comment.repository;

import org.hyunjooon.communication_devtools.domain.post_comment.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    
}
