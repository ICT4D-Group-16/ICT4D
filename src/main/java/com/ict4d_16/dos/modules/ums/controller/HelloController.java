package com.ict4d_16.dos.modules.ums.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "HelloController")
@Tag(name = "HelloController", description = "Hello")
@RequestMapping(path = "/")
//method = RequestMethod.POST,
//consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public @ResponseBody Hello hello(@RequestBody Hello hello) {
        return hello;
    }
}

@Data
@EqualsAndHashCode(callSuper = false)
class Hello {
    private String var1;
    private String var2;
    private String var3;
}