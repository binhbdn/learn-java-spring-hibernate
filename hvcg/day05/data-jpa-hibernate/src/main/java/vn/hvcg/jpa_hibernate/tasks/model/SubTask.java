package vn.hvcg.jpa_hibernate.tasks.model;

import javax.persistence.*;

@Entity
@Table(name = "sub_tasks")
public class SubTask {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String taskName;
   @ManyToOne
   private Task task;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTaskName() {
      return taskName;
   }

   public void setTaskName(String taskName) {
      this.taskName = taskName;
   }

   public Task getTask() {
      return task;
   }

   public void setTask(Task task) {
      this.task = task;
   }
}