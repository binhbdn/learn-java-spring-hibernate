package com.example.hibernate2.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public abstract class GenericDAO<T, ID> implements InterfaceDAO<T, ID> {

    private final Class<T> clazz;

    public GenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    //abstract String getClazzName();

    abstract String getIdProperty();

    @Override
    public List<T> findAll() {
        Session session = SessionFactorySingleton.get().openSession();
        //List<T> list = (List<T>) session.createQuery("from " + getClazzName()).list();
        List<T> list = (List<T>) session.createQuery("from " + clazz.getSimpleName()).list();
        session.close();
        return list;
    }

    @Override
    public T save(T object) {
        Session session = SessionFactorySingleton.get().openSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    @Override
    public T update(T object) {
        Session session = SessionFactorySingleton.get().openSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    @Override
    public Optional<T> findById(ID id) {
        Session session = SessionFactorySingleton.get().openSession();
        //Query query = session.createQuery("from " + getClazzName() + " where " + getIdProperty() + " = :id");
        Query query = session.createQuery("from " + clazz.getSimpleName() + " where " + getIdProperty() + " = :id");
        query.setParameter("id", id);
        List<T> list = (List<T>) query.list();
        session.close();
        if (list == null) {
            return Optional.empty();
        } else if (list.isEmpty()) {
            return Optional.empty();
        } else if (list.size() > 1) {
                throw new RuntimeException("Having more than 2 objects sharing same ID");
        } else {
            return  Optional.of((T)list.get(0));
        }
    }

    @Override
    public List<T> findByIds(List<ID> ids) {
        Session session = SessionFactorySingleton.get().openSession();
        //Query query = session.createQuery("from " + getClazzName() + " where " + getIdProperty() + " = :ids");
        Query query = session.createQuery("from " + clazz.getSimpleName() + " where " + getIdProperty() + " = :ids");
        query.setParameter("ids", ids);
        List<T> list = (List<T>) query.list();
        session.close();

        return list;
    }

    @Override
    public void delete(T object) {
        Session session = SessionFactorySingleton.get().openSession();
        session.delete(object);
    }

    @Override
    public void deleteById(ID id) {
        findById(id).ifPresent(this::delete);
        /*
            Optional<T> op = findById(id);
            if (op.isPresent()) {
            delete(op.get());
        }*/
    }
}
