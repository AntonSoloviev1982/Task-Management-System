package com.solovyev_anton.taskmanagementsystem.repositories;

import com.solovyev_anton.taskmanagementsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Transactional
    Optional<User> findByUsername(String username);

}
