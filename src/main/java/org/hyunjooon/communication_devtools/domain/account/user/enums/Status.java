package org.hyunjooon.communication_devtools.domain.account.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Status {
    ONLINE("온라인"),
    OFFLINE("오프라인"),
    DO_NOT_DISTURBED("방해금지");

    private final String name;
    private static final Map<String, Status> descriptions = Collections.unmodifiableMap(
            Stream.of(values())
                    .collect(Collectors.toMap(Status::getName, Function.identity()))
    );
    public static Status find(String name) {
        return Optional.ofNullable(descriptions.get(name)).orElse(OFFLINE);
    }
}
