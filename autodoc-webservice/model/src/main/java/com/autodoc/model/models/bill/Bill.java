package com.autodoc.model.models.bill;


import com.autodoc.model.models.car.Car;
import com.autodoc.model.enums.Status;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.tasks.Task;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
@Getter @Setter @ToString
public class Bill implements Serializable {

    // Constructors

    public Bill() {
    }



    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @NonNull
    private Date date;

    @NonNull
    private Status status;

    @NonNull
    @ManyToOne
    private Car car;

    @NonNull
    @ManyToOne
    private Client client;

    @NonNull
    @ManyToOne
    private Employee employee;

    @NonNull
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    @NonNull
    private double total;

    @NonNull
    private double vat;

    @NonNull
    private double discount;


}
