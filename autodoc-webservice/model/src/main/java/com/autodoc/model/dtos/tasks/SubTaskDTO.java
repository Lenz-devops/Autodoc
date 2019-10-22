package com.autodoc.model.dtos.tasks;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class SubTaskDTO {

    // Constructors

    @NonNull
    private int id;


    // Parameters
    @Min(value = 1, message = "templateSubTaskId cannot be null")
    private int templateSubTaskId;

    @NotNull(message = "name cannot be null")
    private String name;

    @Min(value = 1, message = "estimated Time cannot be null")
    private double estimatedTime;

    public SubTaskDTO(@NonNull int id, @Min(value = 1, message = "templateSubTaskId cannot be null") int templateSubTaskId, @NotNull(message = "name cannot be null") String name, @Min(value = 1, message = "estimated Time cannot be null") double estimatedTime) {
        this.id = id;
        this.templateSubTaskId = templateSubTaskId;
        this.name = name;
        this.estimatedTime = estimatedTime;
    }
}
