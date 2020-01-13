package com.autodoc.model.dtos.person.client;


import com.autodoc.model.dtos.person.PersonDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClientDTO extends PersonDTO {
    public ClientDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber cannot be null") String phoneNumber) {
        super(lastName, firstName, phoneNumber);
    }

    public ClientDTO() {
    }


    @Override
    public String toString() {
        return "ClientDTO{} " + super.toString();
    }
}
