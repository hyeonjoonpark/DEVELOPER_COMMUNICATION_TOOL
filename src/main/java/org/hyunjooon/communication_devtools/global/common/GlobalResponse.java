package org.hyunjooon.communication_devtools.global.common;

import lombok.Builder;

@Builder
public record GlobalResponse(
        String message,
        Object data
) {

}
