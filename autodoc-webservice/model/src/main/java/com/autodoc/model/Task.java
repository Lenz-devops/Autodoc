package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "task")
@Getter @Setter @ToString
public class Task implements Serializable {

    // Constructors


    public Task() {

    }

    public Task(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToMany
    private List<Bill> bills;


    @ManyToMany
    private List<SubTask> subTasks;

    // to be used for global offers
    private long globalPrice;

}
