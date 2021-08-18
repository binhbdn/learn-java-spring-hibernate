package com.example.hibernate2.dao;

import com.example.hibernate2.model.SchoolClass;
//import org.springframework.stereotype.Component;

//@Component
public class SchoolClassDAO extends GenericDAO<SchoolClass, Integer> {

    public SchoolClassDAO(Class<SchoolClass> clazz) {
        super(clazz);
    }

//    @Override
//    String getClazzName() {
//        return "SchoolClass";
//    }

    @Override
    String getIdProperty() {
        return "id";
    }
}
