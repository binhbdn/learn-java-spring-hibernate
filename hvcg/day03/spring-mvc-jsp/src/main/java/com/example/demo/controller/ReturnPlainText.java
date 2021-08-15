package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReturnPlainText {
    @GetMapping(value = "/text", produces = "text/plain")
    @ResponseBody
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("plain text from @Controller @GetMapping @ResponseBody");
    }
}
