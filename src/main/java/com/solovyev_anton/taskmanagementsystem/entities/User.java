package com.solovyev_anton.taskmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "author")
    private List<Task> authorTasks;
    @OneToMany(mappedBy = "performer")
    private List<Task> performerTasks;

}
