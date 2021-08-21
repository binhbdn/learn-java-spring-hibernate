package com.example.hibernate2.controller;

import com.example.hibernate2.dao.StudentDAO;
import com.example.hibernate2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UIStudentController {
    private final StudentDAO studentDAO;

    @Autowired
    public UIStudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping("/ui-student")
    public ModelAndView searchUser(@RequestParam(value = "name", required = false) String name, ModelAndView mav){

        StringBuilder sb = new StringBuilder();
        List<Student> ans;

        if (name != null) {
            ans = studentDAO.findByName(name);
        } else {
            ans = studentDAO.findAll();
        }

        for(Student student : ans){
            sb.append("<tr><td class=\"std-id\">")
                    .append(student.getId()).append("</td><td class=\"std-name\">")
                    .append(student.getName()).append("</td><td class=\"std-age\">")
                    .append(student.getAge()).append("</td><td>")
                    .append("<button class=\"btn btn-warning btn-edit\">Edit</button>")
                    .append("<button class=\"btn btn-danger ml-3 btn-delete\">Delete</button>")
                    .append("</td></tr>");
        }

        mav.addObject("answers", sb.toString());
        mav.setViewName("answer");

        return mav;
    }

    @PutMapping(value = "/ui-student")
    public String getStudentBirthday(@RequestBody String id) {
        Optional op = studentDAO.findById(Integer.parseInt(id));
        if (op.isPresent()) {
            Student student = (Student) op.get();
            Date date = student.getBirthday();
            if (date != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
                String strDate = dateFormat.format(date);

                return strDate;
            }
        }
        return "null or error during date converting";
    }

    @DeleteMapping(value = "/ui-student")
    public void deleteStudentById(@RequestBody String id) {
        studentDAO.deleteById(Integer.parseInt(id));
    }

    @PostMapping(value = "/ui-student")
    public void addStudent(@RequestBody Student student) {
        studentDAO.update(student);
    }
}
