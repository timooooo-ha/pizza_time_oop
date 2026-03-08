package pizzaconstructor.service;

import pizzaconstructor.model.Ingredient;
import pizzaconstructor.model.Pizza;
import pizzaconstructor.model.PizzaBase;

import java.util.*;

public class DataStore {
    private static DataStore instance;

    private final List<Ingredient> ingredientList = new ArrayList<>();
    private final List<PizzaBase> bases = new ArrayList<>();
    private final List<Pizza> pizzas = new ArrayList<>();

    private DataStore() {
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public List<Ingredient> getIngredientList() {
        return Collections.unmodifiableList(ingredientList);
    }

    public void addBase(PizzaBase base) {
        bases.add(base);
    }

    public List<PizzaBase> getBases() {
        return Collections.unmodifiableList(bases);
    }

    public Optional<PizzaBase> getClassicBase() {
        return bases.stream().filter(b -> b.isClassicBase()).findFirst();
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public List<Pizza> getPizzas() {
        return Collections.unmodifiableList(pizzas);
    }

    public void initSampleData() {
        Ingredient tomato = new Ingredient("Помидоры", 50);
        Ingredient cheese = new Ingredient("Сыр моцарелла", 80);
        Ingredient pepperoni = new Ingredient("Пепперони", 100);
        Ingredient mushrooms = new Ingredient("Грибы", 60);
        Ingredient olives = new Ingredient("Оливки", 40);
        Ingredient basil = new Ingredient("Базилик", 20);
        Ingredient chicken = new Ingredient("Курица", 90);
        Ingredient sesame = new Ingredient("Кунжут", 15);
        ingredientList.addAll(Arrays.asList(tomato, cheese, pepperoni, mushrooms, olives, basil, chicken, sesame));

        PizzaBase classic = new PizzaBase("Классическое", 100, true);
        PizzaBase black = new PizzaBase("Чёрное", 115, false);
        PizzaBase thick = new PizzaBase("Толстое", 110, false);
        bases.addAll(Arrays.asList(classic, black, thick));

        Pizza margherita = new Pizza("Маргарита", classic, Arrays.asList(tomato, cheese, basil));
        Pizza pepperoniPizza = new Pizza("Пепперони", classic, Arrays.asList(tomato, cheese, pepperoni));
        Pizza funghi = new Pizza("Грибная", classic, Arrays.asList(tomato, cheese, mushrooms));
        Pizza chickenPizza = new Pizza("Куриная", classic, Arrays.asList(chicken, cheese, tomato, olives));
        pizzas.addAll(Arrays.asList(margherita, pepperoniPizza, funghi, chickenPizza));
    }
}