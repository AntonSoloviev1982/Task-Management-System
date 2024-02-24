package com.solovyev_anton.taskmanagementsystem.repositories;

import com.solovyev_anton.taskmanagementsystem.entities.Task;
import com.solovyev_anton.taskmanagementsystem.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Page<Task> findAllByAuthor(PageRequest pageRequest, User user);

    Page<Task> findAllByPerformer(PageRequest pageRequest, User user);

}
