package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    // GET /hi HTTP/1.1
    // @GetMapping("/hi")
    @GetMapping(value = "/hi", produces = "text/html")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("<html><head><title>HelloController</title></head><body><h1>Hello world from @Controller</h1></body></html>");
    }
}
