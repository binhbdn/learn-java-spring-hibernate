package vn.hvcg.jpa_hibernate.tasks.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String taskName;
   @Temporal(TemporalType.DATE)
   private Date createdAt;
   @Temporal(TemporalType.DATE)
   private Date dueDate;

   @ManyToMany(cascade = { CascadeType.ALL }, mappedBy="tasks")
   private List<Tag> tags;

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

   public Date getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   public Date getDueDate() {
      return dueDate;
   }

   public void setDueDate(Date dueDate) {
      this.dueDate = dueDate;
   }

   public List<Tag> getTags() {
      return tags;
   }

   public void setTags(List<Tag> tags) {
      this.tags = tags;
   }
}