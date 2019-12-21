package com.autodoc.model.models.tasks;

import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class Task {


    private int id;


    @NotNull
    private String name;


    private String description;

    @NotNull
    private int estimatedTime;


    @NotNull
    private boolean template;

    @NotNull
    private double price;

    private List<Piece> pieces;

    public Task(String name, String description, int estimatedTime, double price, boolean template) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.price = price;
        this.template = template;
    }

    public Task() {
    }


    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                ", template=" + template +
                ", price=" + price +
                ", pieces=" + pieces +
                '}';
    }
}
