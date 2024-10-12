package org.hyunjooon.communication_devtools.domain.post;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "post_id")
    private Long id; // 게시물 ID

    @Column(nullable = false, length = 50) private String title; // 게시물 제목
    @Column(nullable = false, length = 50) private String content; // 게시물 내용

    private String postImageName; // 이미지 이름
    private String postImageUrl; // 이미지 저장 경로

    @Column(nullable = false) @ColumnDefault("0") @Min(0) private int viewCount; // 조회 갯수
    @Column(nullable = false) @ColumnDefault("0") @Min(0) private int likeCount; // 좋아요 갯수

    // 작성자 필드(User) 엔티티 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Setter
    private User user; // 작성자

    @CreatedDate private LocalDateTime createdDate;
    @LastModifiedDate private LocalDateTime lastModifiedDate;

    // 게시물 댓글(PostComment) 엔티티 연관관계 매핑

    @Builder
    public Post(String title, String content, String postImageName, String postImageUrl, int viewCount, int likeCount, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.title = title;
        this.content = content;
        this.postImageName = postImageName;
        this.postImageUrl = postImageUrl;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
