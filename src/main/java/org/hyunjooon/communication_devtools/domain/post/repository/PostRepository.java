package org.hyunjooon.communication_devtools.domain.post.repository;

import org.hyunjooon.communication_devtools.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
