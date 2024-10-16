package org.hyunjooon.communication_devtools.domain.source_code_review;

import jakarta.persistence.*;
import lombok.*;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.source_code.SourceCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class SourceCodeReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "review_id")
    private Long id;

    @Column(nullable = false) private String content;
    @CreatedDate LocalDateTime createdDate;

    // SourceCode 테이블 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "source_code_id") @Setter
    private SourceCode sourceCode;

    // User 테이블 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Setter
    private User user;

    @Builder
    public SourceCodeReview(String content, LocalDateTime createdDate, SourceCode sourceCode, User user) {
        this.content = content;
        this.createdDate = createdDate;
        this.sourceCode = sourceCode;
        this.user = user;
    }
}
