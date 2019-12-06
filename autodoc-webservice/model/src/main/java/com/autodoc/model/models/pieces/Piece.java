package com.autodoc.model.models.pieces;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.tasks.Task;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "piece")
@Getter
@Setter
public class Piece {

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CarModel carModel;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Provider provider;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PieceType pieceType;
    @NonNull
    private String name;
    @NonNull
    private String brand;
    private double buyingPrice;
    private double sellPrice;
    @ManyToMany
    private List<Task> tasks;


    public Piece() {
    }

    public Piece(Provider provider, PieceType pieceType, String name, String brand, double buyingPrice, double sellPrice) {
        this.provider = provider;
        this.pieceType = pieceType;
        this.name = name;
        this.brand = brand;
        this.buyingPrice = buyingPrice;
        this.sellPrice = sellPrice;
    }

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "id=" + id +
                ", carModel=" + carModel +
                ", provider=" + provider +
                ", pieceType=" + pieceType +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", sellPrice=" + sellPrice +
                ", tasks=" + tasks +
                '}';
    }
}
