package com.ict4d_16.dos.modules.ums.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "HelloController")
@Tag(name = "HelloController", description = "Hello")
@RequestMapping("/")
public class HelloController {

    public class Hello {
        public List<String> colors;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Hello hello() {
        Hello hello = new Hello();
        hello.colors = new java.util.ArrayList<String>();
        hello.colors.add("Green");
        hello.colors.add("Blue");
        hello.colors.add("Red");
        return hello;
    }
}
