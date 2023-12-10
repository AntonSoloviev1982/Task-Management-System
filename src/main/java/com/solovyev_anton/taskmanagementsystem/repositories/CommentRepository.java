package com.solovyev_anton.taskmanagementsystem.repositories;

import com.solovyev_anton.taskmanagementsystem.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
