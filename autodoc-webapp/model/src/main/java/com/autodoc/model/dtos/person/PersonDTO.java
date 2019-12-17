package com.autodoc.model.dtos.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;


@Getter
@ToString
@Setter
public abstract class PersonDTO implements Comparable {
    private int id;

    @Size(min = 3, max = 12, message = "{lastName.size}")
    private String lastName;
    @Size(min = 3, max = 12, message = "{firstName.size}")
    private String firstName;
    @Size(min = 8, max = 12, message = "{phoneNumber.size}")
    private String phoneNumber1;
    @Size(min = 8, max = 12, message = "{phoneNumber.size}")
    private String phoneNumber2;

    public PersonDTO(int id, String firstName, String lastName, String phoneNumber1) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber1 = phoneNumber1;
    }

    public PersonDTO() {
    }


    @Override
    public int compareTo(Object o) {
        PersonDTO dto = (PersonDTO) o;
        return Integer.compare(this.getId(), dto.getId());
    }


    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }


}