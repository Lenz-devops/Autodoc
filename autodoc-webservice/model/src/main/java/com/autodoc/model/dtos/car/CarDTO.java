package com.autodoc.model.dtos.car;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CarDTO {

    public CarDTO() {
    }

    private int id;

    @Min(value = 1, message = "carModelId cannot be null")
    private int carModelId;

    @NotNull(message = "registration cannot be null")
    private String registration;

    @Min(value = 1, message = "clientId cannot be null")
    private int clientId;



    public CarDTO(String registration, int carModelId, int clientId) {
        this.registration = registration;
        this.carModelId = carModelId;
        this.clientId = clientId;
    }


}
