package vn.hvcg.jpa_hibernate.student.controller;

import vn.hvcg.jpa_hibernate.student.repository.StudentRepository;
import vn.hvcg.jpa_hibernate.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UIStudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public UIStudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/ui-student")
    public ModelAndView searchUser(@RequestParam(value = "name", required = false) String namePattern,
                                   @RequestParam(value = "age", required = false) Integer minAge,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "20") int size,
                                   ModelAndView mav){

        List<Student> ans;

        if (namePattern != null) {
            String namePatternSQL = "%" + namePattern + "%";
            ans = studentRepository.findByName(namePatternSQL);
        } else if (minAge != null) {
            Page<Student> pages = studentRepository.findByAgeGreaterThan(minAge, PageRequest.of(page-1, size));
            ans = pages.getContent();
            mav.addObject("total_pages",pages.getTotalPages());
            mav.addObject("totals",pages.getTotalElements());
        } else {
            ans = studentRepository.findAll();
        }

        StringBuilder sb = new StringBuilder();
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
        Optional op = studentRepository.findById(Integer.parseInt(id));
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
        studentRepository.deleteById(Integer.parseInt(id));
    }

    @PostMapping(value = "/ui-student")
    public void addStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }
}
