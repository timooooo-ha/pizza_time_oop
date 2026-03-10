package pizzaconstructor.model;

import pizzaconstructor.interfaces.Displayable;
import pizzaconstructor.interfaces.Identifiable;
import pizzaconstructor.interfaces.Priceable;

import java.util.UUID;

public abstract class OrderItem implements Identifiable, Priceable, Displayable {
    private final UUID id;
    private PizzaSize size;
    private Crust crust;

    protected OrderItem(PizzaSize size, Crust crust) {
        this.id = UUID.randomUUID();
        this.size = size;
        this.crust = crust;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public PizzaSize getSize() {
        return size;
    }

    public Crust getCrust() {
        return crust;
    }

    protected double getCrustPrice() {
        return crust != null ? crust.getPrice() : 0;
    }
}