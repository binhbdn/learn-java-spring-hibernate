package vn.hvcg.jpa_hibernate.student.controller;

import vn.hvcg.jpa_hibernate.student.repository.SchoolClassRepository;
import vn.hvcg.jpa_hibernate.student.repository.StudentRepository;
import vn.hvcg.jpa_hibernate.student.model.SchoolClass;
import vn.hvcg.jpa_hibernate.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SchoolController {

    private final StudentRepository studentRepository;
    private final SchoolClassRepository schoolClassRepository;

    //[ n controller, n StudentRepository ] <- singleton;
    @Autowired
    public SchoolController(StudentRepository studentRepository, SchoolClassRepository schoolClassRepository) {
        // SINGLETON -> POJO (plain old java object)
        // JAVA Bean -> Spring Bean -> Spring Autowired
        this.studentRepository = studentRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    @GetMapping(value = "/st")
    public Student fetchStudents(@RequestParam("id") int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "/student")
    public ResponseEntity<?> fetchStudents(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name
    ) {
        if (name != null) {
            return ResponseEntity.ok(studentRepository.findByName(name));
        } else if (id == null) {
            return ResponseEntity.ok(studentRepository.findAll());
        } else {
            Optional<Student> studentOp = studentRepository.findById(id);
            if (studentOp.isPresent()) {
                return ResponseEntity.ok(studentOp.get());
            } else {
                return ResponseEntity.ok("This student is not exist");
            }
        }
    }

    @PostMapping(value = "/student")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping(value = "/school_class")
    public List<SchoolClass> fetchClazzes() {
        return schoolClassRepository.findAll();
    }

    @PostMapping(value = "/school_class")
    public SchoolClass createClazz(@RequestBody SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

}
