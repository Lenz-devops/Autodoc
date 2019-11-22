package com.autodoc.model.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client extends Person {


    public Client() {
    }


    public Client(int id, String firstName, String lastName, String phoneNumber1) {
        super(id, firstName, lastName, phoneNumber1);
    }

    @Override
    public String toString() {
        return "Client{} " + super.toString();
    }
}
