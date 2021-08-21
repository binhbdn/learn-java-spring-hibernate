package vn.hvcg.jpa_hibernate.tasks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    //Paginition template
    @GetMapping("/tasks")
    public ResponseEntity<?> fetchTask(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
        return null;
    }
}
