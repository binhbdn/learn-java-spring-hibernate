package vn.hvcg.security.student.repository;

import vn.hvcg.security.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("Select s from Student s where s.name LIKE ?1")
    List<Student> findByName(String name);

    //Page<Student> findByName(String name, Pageable page);

    Page<Student> findByAgeGreaterThan(int age, Pageable page);
}
