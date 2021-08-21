package vn.hvcg.jpa_hibernate.tasks.repository;


import vn.hvcg.jpa_hibernate.tasks.model.SubTask;
import vn.hvcg.jpa_hibernate.tasks.model.Task;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface SubTaskRepository extends PagingAndSortingRepository<SubTask, Integer> {
//public interface SubTaskRepository extends CrudRepository<SubTask, Integer> {
//public interface SubTaskRepository extends JpaRepository<SubTask, Integer> {
   List<SubTask> findByTask(Task task);
}