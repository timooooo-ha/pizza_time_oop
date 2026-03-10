package pizzaconstructor.model;

import pizzaconstructor.interfaces.Displayable;
import pizzaconstructor.interfaces.Identifiable;
import pizzaconstructor.interfaces.Priceable;

import java.util.UUID;

public class Ingredient implements Identifiable, Priceable, Displayable{
    private final UUID id;
    private String name;
    private double price;

    public Ingredient(String name, double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String getFullInfo() {
        return String.format("%s - %.2f руб. [%s]", name, price, id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getFullInfo();
    }

    @Override
    public UUID getId() {
        return id;
    }
}