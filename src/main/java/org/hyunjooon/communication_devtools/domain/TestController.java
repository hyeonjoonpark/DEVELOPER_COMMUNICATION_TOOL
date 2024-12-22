package org.hyunjooon.communication_devtools.domain;

import org.springframework.graphql.data.method.annotation.QueryMapping;
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
    @QueryMapping(value = "testQuery")
    public String test1() {
        return "test1";
    }
}
