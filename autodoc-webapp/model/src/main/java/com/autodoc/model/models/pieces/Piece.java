package com.autodoc.model.models.pieces;

import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.provider.Provider;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class Piece {


    private int id;
    private List<CarModel> carModels;
    private Provider provider;
    private PieceType pieceType;
    @NonNull
    private String name;
    @NonNull
    private String brand;
    private long buyingPrice;
    private long sellPrice;

    public Piece() {
    }


    public Piece(Provider provider, PieceType pieceType, String name, String brand, long buyingPrice, long sellPrice) {
        this.provider = provider;
        this.pieceType = pieceType;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
    }
}
