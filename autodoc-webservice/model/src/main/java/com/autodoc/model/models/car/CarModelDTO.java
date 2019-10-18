package com.autodoc.model.models.car;

import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class CarModelDTO /*implements Serializable*/ {

    // Constructors


    public CarModelDTO() {
    }

    public CarModelDTO(@NonNull Manufacturer manufacturer, @NonNull String name, @NonNull String description, @NonNull GearBox gearbox, @NonNull String engine, @NonNull FuelType fuelType) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
        this.gearbox = gearbox;
        this.engine = engine;
        this.fuelType = fuelType;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @NonNull
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private transient List<Car> cars;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private transient List<Piece> pieces;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private String description;

    @NonNull
    @Enumerated(EnumType.STRING)
    private GearBox gearbox;

    @NonNull
    private String engine;

    @NonNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", manufacturer=" + manufacturer +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gearbox=" + gearbox +
                ", engine='" + engine + '\'' +
                ", fuelType=" + fuelType +
                '}';
    }
}
