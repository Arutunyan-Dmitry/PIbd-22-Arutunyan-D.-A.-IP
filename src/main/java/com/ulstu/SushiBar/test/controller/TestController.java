package com.ulstu.SushiBar.test.controller;

import com.ulstu.SushiBar.WebConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ulstu.SushiBar.test.model.TestDto;

import javax.validation.Valid;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/test")
public class TestController {
    @PostMapping
    public TestDto testValidation(@RequestBody @Valid TestDto testDto) {
        return testDto;
    }
}
