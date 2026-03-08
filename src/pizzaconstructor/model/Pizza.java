package pizzaconstructor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pizza {
    private String name;
    private List<Ingredient> ingredientList;
    private PizzaBase base;

    public Pizza(String name, PizzaBase base, List<Ingredient> ingredientList) {
        if (base == null) {
            throw new IllegalArgumentException("Основа обязательна для пиццы");
        }
        this.base = base;
        this.ingredientList = ingredientList;
        this.name = name;
    }

    public double getIngredientsPrice() {
        return ingredientList.stream().mapToDouble(Ingredient::getPrice).sum();
    }

    public double getPrice() {
        return base.getPrice() + getIngredientsPrice();
    }

    public String getFullInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %.2f руб.", name, getPrice()));
        sb.append("\n Основа: ").append(base.getName());
        sb.append(String.format(" (%.2f руб.)", base.getPrice()));
        sb.append("\n Ингредиенты: ");
        if (ingredientList.isEmpty()) {
            sb.append("нет");
        } else {
            for (int i = 0; i < ingredientList.size(); i++) {
                if (i > 0)
                    sb.append(", ");
                sb.append(ingredientList.get(i).getName());
            }
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredientList() {
        return Collections.unmodifiableList(ingredientList);
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = new ArrayList<>(ingredientList);
    }

    public void setBase(PizzaBase base) {
        if (base == null)
            throw new IllegalArgumentException("Основа обязательная для пиццы");
        this.base = base;
    }

    @Override
    public String toString() {
        return getFullInfo();
    }
}