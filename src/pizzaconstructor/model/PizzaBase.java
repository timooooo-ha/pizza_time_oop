package pizzaconstructor.model;

import pizzaconstructor.interfaces.Displayable;
import pizzaconstructor.interfaces.Identifiable;
import pizzaconstructor.interfaces.Priceable;

import java.util.UUID;

public class PizzaBase implements Identifiable, Priceable, Displayable{
    private final UUID id;
    private String name;
    private double price;
    private boolean classicBase;

    public PizzaBase(String name, double price, boolean classicBase) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.classicBase = classicBase;
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
        String type = classicBase ? " [классическая]" : "";
        return String.format("%s - %.2f руб.%s [%s]", name, price, type, id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isClassicBase() {
        return classicBase;
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