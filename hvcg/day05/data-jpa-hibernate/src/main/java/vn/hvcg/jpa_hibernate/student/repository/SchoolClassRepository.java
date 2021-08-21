package vn.hvcg.jpa_hibernate.student.repository;

import vn.hvcg.jpa_hibernate.student.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
}
