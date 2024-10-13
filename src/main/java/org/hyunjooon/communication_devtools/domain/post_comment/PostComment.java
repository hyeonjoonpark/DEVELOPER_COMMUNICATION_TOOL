package org.hyunjooon.communication_devtools.domain.post_comment;

import jakarta.persistence.*;
import lombok.*;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.board.Board;
import org.hyunjooon.communication_devtools.domain.post.Post;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PostComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "board_comment_id")
    private Long id; // 게시판 ID

    @Column(nullable = false) private String comment; // 댓글
    @CreatedDate @Column(nullable = false) private LocalDateTime createdDate; // 댓글 작성 시간

    // 작성자 User 테이블 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Setter
    private User user;

    // 게시물 Post 테이블 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "post_id") @Setter
    private Post post;

    @Builder
    public PostComment(String comment, LocalDateTime createdDate, User user, Post post) {
        this.comment = comment;
        this.createdDate = createdDate;
        this.user = user;
        this.post = post;
    }
}
