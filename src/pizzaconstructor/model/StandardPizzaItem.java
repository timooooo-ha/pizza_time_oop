package pizzaconstructor.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StandardPizzaItem extends OrderItem {
    private final Pizza pizza;
    private final Set<UUID> doubledIngredientIds;

    public StandardPizzaItem(Pizza pizza, PizzaSize size, Crust crust, Set<UUID> doubledIngredientIds) {
        super(size, crust);
        this.pizza = pizza;
        this.doubledIngredientIds = doubledIngredientIds != null
                ? new HashSet<>(doubledIngredientIds)
                : new HashSet<>();
    }

    @Override
    public double getPrice() {
        double total = pizza.getBase().getPrice();
        for (Ingredient ing : pizza.getIngredientList()) {
            double p = ing.getPrice();
            if (doubledIngredientIds.contains(ing.getId())) {
                p *= 2;
            }
            total += p;
        }
        total += getCrustPrice();
        return total * getSize().getMultiplier();
    }

    @Override
    public String getDisplayName() {
        return pizza.getDisplayName() + " (" + getSize().getDisplayName() + ")";
    }

    @Override
    public String getFullInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("  Пицца: %s | Размер: %s | Цена: %.2f руб.\n",
                pizza.getDisplayName(), getSize().getDisplayName(), getPrice()));
        if (!doubledIngredientIds.isEmpty()) {
            sb.append("    Удвоенные: ");
            boolean first = true;
            for (Ingredient ing : pizza.getIngredientList()) {
                if (doubledIngredientIds.contains(ing.getId())) {
                    if (!first) sb.append(", ");
                    sb.append(ing.getDisplayName());
                    first = false;
                }
            }
            sb.append("\n");
        }
        if (getCrust() != null) {
            sb.append("    Бортик: ").append(getCrust().getDisplayName()).append("\n");
        }
        return sb.toString();
    }

    public Pizza getPizza() {
        return pizza;
    }

    public Set<UUID> getDoubledIngredientIds() {
        return doubledIngredientIds;
    }

    @Override
    public String toString() {
        return getFullInfo();
    }
}