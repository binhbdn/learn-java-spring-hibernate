package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReturnAppJson {
    @GetMapping(value = "/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("{\"how\":\"working\"}");
    }
}
