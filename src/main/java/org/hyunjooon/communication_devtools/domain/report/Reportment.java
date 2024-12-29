package org.hyunjooon.communication_devtools.domain.report;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hyunjooon.communication_devtools.domain.report.enums.Importance;
import org.hyunjooon.communication_devtools.domain.report.enums.ReportType;
import org.hyunjooon.communication_devtools.global.common.BaseTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reportment extends BaseTime {
    @Id @Column(name = "reportment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "report_title") private String title;
    @Column(name = "report_content") private String content;
    @Column(name = "report_type") @Enumerated(value = EnumType.STRING) private ReportType type;
    @Column(name = "report_importance") @Enumerated(value = EnumType.STRING) private Importance importance;

    @Builder
    public Reportment(String title, String content, ReportType type, Importance importance) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.importance = importance;
    }
}
