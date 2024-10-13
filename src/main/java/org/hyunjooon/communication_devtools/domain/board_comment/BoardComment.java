package org.hyunjooon.communication_devtools.domain.board_comment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.board.Board;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BoardComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "board_comment_id")
    private Long id; // 게시판 ID

    @Column(nullable = false) private String comment; // 댓글
    @CreatedDate @Column(nullable = false) private LocalDateTime createdDate; // 댓글 작성 시간

    // 작성자 User 테이블 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Setter
    private User user;

    // 게시판 Board 테이블 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "board_id") @Setter
    private Board board;
}
