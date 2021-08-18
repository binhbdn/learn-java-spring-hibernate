package com.example.hibernate1.controller;

import com.example.hibernate1.dao.SessionFactorySingleton;
import com.example.hibernate1.model.SchoolClass;
import com.example.hibernate1.model.Student;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @GetMapping(value = "/student")
    public List<Student> fetchStudents(){
        Session session = SessionFactorySingleton.get().openSession();
        //session.beginTransaction();
        List<Student> results = (List<Student>) session.createQuery( "from Student" ).list(); // HQL - Hibernate Query Language
        for ( Student student : (List<Student>) results ) {
            System.out.println( "Student: " + student.toString());
        }
        //session.getTransaction().commit();
        session.close();

        return results;
    }

    @GetMapping(value = "/school_class")
    public List<SchoolClass> fetchClazzes(){
        Session session = SessionFactorySingleton.get().openSession();
        //session.beginTransaction();
        List<SchoolClass> results = (List<SchoolClass>) session.createQuery( "from SchoolClass" ).list(); // HQL - Hibernate Query Language
        for ( SchoolClass clazz : (List<SchoolClass>) results ) {
            System.out.println( "Class: " + clazz.toString());
        }
        //session.getTransaction().commit();
        session.close();

        return results;
    }

    @PostMapping(value = "/student")
    public Student createStudent(@RequestBody Student student){
        Session session = SessionFactorySingleton.get().openSession();
        session.beginTransaction();
        session.saveOrUpdate(student);
        session.getTransaction().commit();
        session.close();

        return student;
    }
}
