package pizzaconstructor.model;

import pizzaconstructor.interfaces.Displayable;
import pizzaconstructor.interfaces.Identifiable;
import pizzaconstructor.interfaces.Priceable;

import java.util.*;

public class Crust implements Identifiable, Priceable, Displayable{
    private final UUID id;
    private String name;
    private List<Ingredient> ingredientList;
    private boolean useWhitelist;
    private Set<UUID> pizzaIds;

    public Crust(String name, List<Ingredient> ingredientList, boolean useWhitelist) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.ingredientList = new ArrayList<>(ingredientList);
        this.useWhitelist = useWhitelist;
        this.pizzaIds = new HashSet<>();
    }

    public boolean canBeUsedWith(Pizza pizza) {
        if (pizzaIds.isEmpty()) {
            return !useWhitelist;
        }
        if (useWhitelist) {
            return pizzaIds.contains(pizza.getId());
        } else {
            return !pizzaIds.contains(pizza.getId());
        }
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public double getPrice() {
        return ingredientList.stream().mapToDouble(p -> p.getPrice()).sum();
    }

    @Override
    public String getFullInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s — %.2f руб. [%s]", name, getPrice(), id));
        sb.append("\n  Ингредиенты: ");
        for (int i = 0; i < ingredientList.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(ingredientList.get(i).getDisplayName());
        }
        sb.append("\n  Режим: ").append(useWhitelist ? "белый список" : "чёрный список");
        sb.append(" (пицц в списке: ").append(pizzaIds.size()).append(")");
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

    public boolean isUseWhitelist() {
        return useWhitelist;
    }

    public void addPizzaId(UUID pizzaId) {
        pizzaIds.add(pizzaId);
    }

    public void setUseWhitelist(boolean useWhitelist) {
        this.useWhitelist = useWhitelist;
    }

    public void setPizzaIds(Set<UUID> pizzaIds) {
        this.pizzaIds = new HashSet<>(pizzaIds);
    }
}