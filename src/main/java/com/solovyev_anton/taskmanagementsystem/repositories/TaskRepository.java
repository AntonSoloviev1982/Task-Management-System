package com.solovyev_anton.taskmanagementsystem.repositories;

import com.solovyev_anton.taskmanagementsystem.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
