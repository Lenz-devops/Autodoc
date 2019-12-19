package com.autodoc.model.dtos.pieces;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@ToString
public class PieceDTO {

    public PieceDTO() {
    }

    public PieceDTO(  @Min(value = 1, message = "pieceTypeId cannot be null") int pieceTypeId, @NotNull(message = "name cannot be null") String name, @NotNull(message = "brand cannot be null") String brand, @Min(value = 1, message = "buyingPrice cannot be null") long buyingPrice, @Min(value = 1, message = "sellPrice cannot be null") long sellPrice) {

        this.pieceTypeId = pieceTypeId;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
    }

    private int id;


    private int carModelId;


    @Min(value = 1, message = "pieceTypeId cannot be null")
    private int pieceTypeId;

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "brand cannot be null")
    private String brand;

    @Min(value = 1, message = "buyingPrice cannot be null")
    private long buyingPrice;

    @Min(value = 1, message = "sellPrice cannot be null")
    private long sellPrice;


}
