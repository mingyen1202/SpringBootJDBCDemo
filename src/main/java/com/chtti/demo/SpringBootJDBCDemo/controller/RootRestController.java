package com.chtti.demo.SpringBootJDBCDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRestController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String index() {
        return "hello spring with JDBC";
    }
}
