package org.hyunjooon.communication_devtools.domain.proceedings;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hyunjooon.communication_devtools.global.common.BaseTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Proceedings extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proceedings_id") @Comment("회의록 ID") private Long id;

    @Column(name = "proceedings_title") private String title;
    @Column(name = "proceedings_content") private String content;
}
