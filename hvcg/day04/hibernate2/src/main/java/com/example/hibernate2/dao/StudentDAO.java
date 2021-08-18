package com.example.hibernate2.dao;

import com.example.hibernate2.model.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
//import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class StudentDAO extends GenericDAO<Student, Integer> {

    public StudentDAO(Class<Student> clazz) {
        super(clazz);
    }

//    @Override
//    String getClazzName() {
//        return "Student";
//    }

    @Override
    String getIdProperty() {
        return "id";
    }

    public List<Student> findByName(String namePattern) {
        Session session = SessionFactorySingleton.get().openSession();
        String namePatternSQL = "%" + namePattern + "%";
        Query query = session.createQuery("from Student where name LIKE :namePattern");
        query.setParameter("namePattern", namePatternSQL);
        List list = query.list();
        session.close();

        return list;
    }
}
