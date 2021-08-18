package com.example.hibernate2.controller;

import com.example.hibernate2.dao.SchoolClassDAO;
import com.example.hibernate2.dao.StudentDAO;
import com.example.hibernate2.model.SchoolClass;
import com.example.hibernate2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SchoolController {

    private final StudentDAO studentDAO;
    private final SchoolClassDAO schoolClassDAO;

    //[ n controller, n StudentDAO ] <- singleton;
    @Autowired
    public SchoolController(StudentDAO studentDAO, SchoolClassDAO schoolClassDAO) {
        // SINGLETON -> POJO (plain old java object)
        // JAVA Bean -> Spring Bean -> Spring Autowired
        this.studentDAO = studentDAO;
        this.schoolClassDAO = schoolClassDAO;
    }

    @GetMapping(value = "/st")
    public Student fetchStudents(@RequestParam("id") int id) {
        return studentDAO.findById(id).orElse(null);
    }

    @GetMapping(value = "/student")
    public ResponseEntity<?> fetchStudents(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name
    ) {
        if (name != null) {
            return ResponseEntity.ok(studentDAO.findByName(name));
        } else if (id == null) {
            return ResponseEntity.ok(studentDAO.findAll());
        } else {
            Optional<Student> studentOp = studentDAO.findById(id);
            if (studentOp.isPresent()) {
                return ResponseEntity.ok(studentOp.get());
            } else {
                return ResponseEntity.ok("This student is not exist");
            }
        }
    }

    @PostMapping(value = "/student")
    public Student createStudent(@RequestBody Student student) {
        return studentDAO.save(student);
    }

    @GetMapping(value = "/school_class")
    public List<SchoolClass> fetchClazzes() {
        return schoolClassDAO.findAll();
    }

    @PostMapping(value = "/school_class")
    public SchoolClass createClazz(@RequestBody SchoolClass schoolClass) {
        return schoolClassDAO.save(schoolClass);
    }

}
