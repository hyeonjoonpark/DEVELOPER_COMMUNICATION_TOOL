package org.hyunjooon.communication_devtools.domain;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String test() {
        return "test";
    }
    // @PreAuthorize : 메서드 호출 전에 권한 검사
    // @PostAuthorize : 메서드 호출 후 응답 전에 권한 검사
    @PreAuthorize("isAuthenticated()") // SecurityConfig에서 @EnableMethodSecurity 어노테이션
    @QueryMapping(value = "testQuery")
    public String test1() {
        return "test1";
    }
}
