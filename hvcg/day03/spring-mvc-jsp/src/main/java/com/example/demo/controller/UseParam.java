package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UseParam {
    @GetMapping(value = "/hi", produces = "text/plain")
    @ResponseBody
    public String hello(@RequestParam("name") String name){
        return "Hello " + name;
    }
}
