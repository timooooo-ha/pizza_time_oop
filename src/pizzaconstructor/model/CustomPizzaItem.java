package pizzaconstructor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomPizzaItem extends OrderItem {
    private final String name;
    private final List<Ingredient> ingredients;
    private final PizzaBase base;

    public CustomPizzaItem(String name, PizzaBase base, List<Ingredient> ingredients,
                           PizzaSize size, Crust crust) {
        super(size, crust);
        this.name = name;
        this.base = base;
        this.ingredients = new ArrayList<>(ingredients);
    }

    @Override
    public double getPrice() {
        double total = base.getPrice();
        total += ingredients.stream().mapToDouble(Ingredient::getPrice).sum();
        total += getCrustPrice();
        return total * getSize().getMultiplier();
    }

    @Override
    public String getDisplayName() {
        return name + " (своя, " + getSize().getDisplayName() + ")";
    }

    @Override
    public String getFullInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("  Своя пицца: %s | Размер: %s | Цена: %.2f руб.\n",
                name, getSize().getDisplayName(), getPrice()));
        sb.append("    Основа: ").append(base.getDisplayName()).append("\n");
        sb.append("    Ингредиенты: ");
        for (int i = 0; i < ingredients.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(ingredients.get(i).getDisplayName());
        }
        sb.append("\n");
        if (getCrust() != null) {
            sb.append("    Бортик: ").append(getCrust().getDisplayName()).append("\n");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getFullInfo();
    }
}
