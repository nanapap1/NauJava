package ru.MarkMoskvitin.NauJava.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String body;
    @Column
    private String link;
    @Column
    private String end;
    @ManyToOne
    @Column(nullable = false)
    private User user;
    @ManyToOne
    @Column(nullable = false)
    private Push push;
    @ManyToMany
    @Column(nullable = false)
    private List<Subtask> subtaskList;
    @ManyToOne
    @Column(nullable = false)
    private Group group;


}
