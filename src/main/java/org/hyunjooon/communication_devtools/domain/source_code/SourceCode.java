package org.hyunjooon.communication_devtools.domain.source_code;

import jakarta.persistence.*;
import lombok.*;
import org.hyunjooon.communication_devtools.domain.account.user.User;
import org.hyunjooon.communication_devtools.domain.source_code_review.SourceCodeReview;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

// SourceCode 리뷰를 위한 엔티티
@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class SourceCode {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "source_code_id")
    private Long id;

    @Column(length = 5000) private String sourceCode;

    // 작성자 User 테이블 매핑
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Setter
    private User user;

    @CreatedDate LocalDateTime createdDate;

    @OneToMany(mappedBy = "sourceCode", cascade = CascadeType.ALL)
    private List<SourceCodeReview> sourceCodeReview;

    public void addSourceCodeReview(SourceCodeReview sourceCodeReview) {
        sourceCodeReview.setSourceCode(this);
        this.sourceCodeReview.add(sourceCodeReview);
    }

    @Builder
    public SourceCode(String sourceCode, User user, LocalDateTime createdDate, List<SourceCodeReview> sourceCodeReview) {
        this.sourceCode = sourceCode;
        this.user = user;
        this.createdDate = createdDate;
        this.sourceCodeReview = sourceCodeReview;
    }
}
