package com.autodoc.model.dtos.bill;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BillDTO {

    private static final double VAT = 00.195;
    private int id;


    //@FutureOrPresent(message = "date should not be in the past")
    //@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private String dateReparation;

    @NotNull
    private String status;

    @NotNull
    private List<Integer> tasks;

    @NotNull
    private List<Integer> pieces;

    @NotNull(message = "car Registration cannot be null")
    private String registration;

    @Min(value = 1, message = "clientId cannot be null")
    private int clientId;

    @Min(value = 1, message = "employeeId cannot be null")
    private int employeeId;

    @NotNull
    private double vat;

    @Min(value = 1, message = "total cannot be null")
    private double total;
    private double discount;

    private String comments;

    public BillDTO() {
    }


}
