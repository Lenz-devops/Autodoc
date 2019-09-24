package com.autodoc.model.models.tasks;

import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.person.employee.Employee;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subTask")
@Getter @Setter @ToString
public class SubTask implements Serializable {

    // Constructors


    public SubTask() {
    }


    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;


    @ManyToMany
    @NonNull
    private List<Task> tasks;

    @OneToMany
    @NonNull
    private List<Piece> pieces;

    @ManyToMany
    private List<Employee> employees;

    @ManyToOne
    @NonNull
    private TemplateSubTask templateSubTask;

    @NonNull
    private String name;

    @NonNull
    private double estimatedTime;


}
