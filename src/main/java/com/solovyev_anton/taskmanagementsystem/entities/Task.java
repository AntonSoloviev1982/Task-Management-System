package com.solovyev_anton.taskmanagementsystem.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    @ManyToOne
    private User author;
    @ManyToOne
    private User performer;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;


}
