package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
@Setter @Getter  @ToString
public class Category implements Serializable {

    // Constructors


    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToMany
    private List<PieceType> pieceTypes;

    private String name;
}
