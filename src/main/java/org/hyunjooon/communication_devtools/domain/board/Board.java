package org.hyunjooon.communication_devtools.domain.board;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.board_comment.BoardComment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "board_id")
    private Long id; // 게시물 ID

    @Column(nullable = false, length = 50) private String title; // 게시물 제목
    @Column(nullable = false, length = 1000) private String content; // 게시물 내용

    private String boardImageName; // 게시물 이미지 이름
    private String boardImageUrl; // 게시물 이미지 경로

    // 작성자 (User) 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Setter
    private User user; // 작성자

    @Column(nullable = false) @CreatedDate private LocalDateTime createdDate; // 등록일자
    @Column(nullable = false) @LastModifiedDate private LocalDateTime lastModifiedDate; // 마지막 수정일자

    @Column(nullable = false) @ColumnDefault("0") @Min(0) private int viewCount; // 조회 갯수
    @Column(nullable = false) @ColumnDefault("0") @Min(0) private int likeCount; // 좋아요 갯수

    // 댓글(BoardComment) 연관관계 매핑
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardComment> comments = new ArrayList<>();

    public void addComment(BoardComment comment) {
        comment.setBoard(this);
        this.comments.add(comment);
    }

    @Builder
    public Board(String title, String content, String boardImageName, String boardImageUrl, LocalDateTime createdDate, LocalDateTime lastModifiedDate, int viewCount, int likeCount) {
        this.title = title;
        this.content = content;
        this.boardImageName = boardImageName;
        this.boardImageUrl = boardImageUrl;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }
}
