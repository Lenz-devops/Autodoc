package com.autodoc.model.dtos.bill;


import com.autodoc.model.enums.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class BillDTO {




    private int id;



    @FutureOrPresent(message = "date should nto be in the past")
    private Date date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Min(value = 1, message = "carId cannot be null")
    private int carId;

    @Min(value = 1, message = "clientId cannot be null")
    private int clientId;

    @Min(value = 1, message = "employeeId cannot be null")
    private int employeeId;

    @Min(value = 1, message = "total cannot be null")
    private double total;

    private static final double VAT = 00.195;

    private double discount;

    public BillDTO(@FutureOrPresent(message = "date should nto be in the past") Date date, @NotNull Status status, @Min(value = 1, message = "carId cannot be null") int carId, @Min(value = 1, message = "clientId cannot be null") int clientId, @Min(value = 1, message = "employeeId cannot be null") int employeeId, @Min(value = 1, message = "total cannot be null") double total) {
        this.date = date;
        this.status = status;
        this.carId = carId;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.total = total;
    }
}
