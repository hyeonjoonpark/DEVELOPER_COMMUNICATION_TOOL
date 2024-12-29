package org.hyunjooon.communication_devtools.domain.report.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportType {
    SYSTEM_ERROR_REPORT("service error"),
    USER_REPORT("user"),
    USER_FEEDBACT_REPORT("user feedback");

    private final String typeDescription;
}
