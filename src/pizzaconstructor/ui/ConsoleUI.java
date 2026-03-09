package pizzaconstructor.ui;

import pizzaconstructor.model.Ingredient;
import pizzaconstructor.model.PizzaBase;
import pizzaconstructor.service.DataStore;
import pizzaconstructor.interfaces.Displayable;
import pizzaconstructor.interfaces.Priceable;
import pizzaconstructor.interfaces.Identifiable;

import java.util.*;

public class ConsoleUI {
    private final Scanner scanner;
    private final DataStore store;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        store = DataStore.getInstance();
    }

    public void mainMenu() {
        System.out.println("=========================");
        System.out.println(" КОНСТРУКТОР PIZZA TIME");
        System.out.println("=========================");

        while (true) {
            System.out.println("\n--- Главное меню ---");
            System.out.println("1. Ингредиенты");
            System.out.println("2. Основы для пиццы");
            System.out.println("3. Пиццы");
            System.out.println("4. Бортики");
            System.out.println("5. Заказы");
            System.out.println("0. Выход");
            System.out.print("Выберите: ");

            switch (readInt()) {
                case 1: ingredientMenu(); break;
                case 2: baseMenu(); break;
//                case 3: pizzaMenu(); break;
                case 0:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void ingredientMenu() {
        while (true) {
            System.out.println("\n--- Ингредиенты ---");
            System.out.println("1. Добавить");
            System.out.println("2. Редактировать");
            System.out.println("3. Удалить");
            System.out.println("4. Список");
            System.out.println("0. Назад");
            System.out.print("Выберите: ");

            switch (readInt()) {
                case 1: createIngredient(); break;
                case 2: editIngredient(); break;
                case 3: deleteIngredient(); break;
                case 4: listIngredients(); break;
                case 0: return;
                default: System.out.println("Неверный выбор.");
            }
        }
    }

    private void createIngredient() {
        System.out.println("Название: ");
        String name = readLine();
        System.out.println("Стоимость: ");
        double price = readDouble();
        if (price < 0) {
            System.out.println("Стоимость не может быть отрицательной.");
            return;
        }
        Ingredient ing = new Ingredient(name, price);
        store.addIngredient(ing);
        System.out.println("Добавлено: " + ing.getFullInfo());
    }

    private void deleteIngredient() {
        List<Ingredient> list = store.getIngredientList();
        if (list.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        Ingredient ing = selectFromList(list, "Выберите ингредиент для удаления:");
        if (ing == null) {
            return;
        }

        if (store.isIngredientUsed(ing.getId())) {
            System.out.println("Невозможно удалить - ингредиент используется в пиццах");
            return;
        }
        store.removeIngredient(ing.getId());
        System.out.println("Удалено: " + ing.getDisplayName());
    }

    private void editIngredient() {
        List<Ingredient> list = store.getIngredientList();
        if (list.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }

        Ingredient ing = selectFromList(list, "Выберите ингредиент");
        if (ing == null) {
            return;
        }

        System.out.println("Новое название (Enter - оставить " + ing.getName() + ")");
        String name = readLine();
        if (!name.isEmpty()) {
            ing.setName(name);
        }

        System.out.println("Новая стоимость (Enter - оставить " + String.format("%.2f", ing.getPrice()) + ")");
        String priceStr = readLine();
        if (!priceStr.isEmpty()) {
            try {
                double price = Double.parseDouble(priceStr.replace(',', '.'));
                if (price >= 0) {
                    ing.setPrice(price);
                } else {
                    System.out.println("Стоимость не может быть отрицательной");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа");
            }
        }
        System.out.println("Обновлено: " + ing.getFullInfo());
    }

    private void listIngredients() {
        System.out.println("Фильтр по названию (Enter - все)");
        String filter = readLine();

        List<Ingredient> list = store.getIngredientList();

        System.out.println("\n--- Ингредиенты (" + list.size() + ") ---");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getFullInfo());
        }
        System.out.println("------------------------");
    }

    private void baseMenu() {
        while (true) {
            System.out.println("\n--- Основы для пиццы ---");
            System.out.println("1. Добавить");
            System.out.println("2. Редактировать");
            System.out.println("3. Удалить");
            System.out.println("4. Список");
            System.out.println("0. Назад");
            System.out.print("Выберите: ");

            switch (readInt()) {
                case 1: createBase(); break;
                case 2: editBase(); break;
//                case 3: deleteBase(); break;
//                case 4: listBases(); break;
                case 0: return;
                default: System.out.println("Неверный выбор.");
            }
        }
    }

    private void createBase() {
        System.out.print("Название: ");
        String name = readLine();
        if (name.equals("классическая")) {
            System.out.println("Нельзя использовать название \"классическая\"");
            return;
        }
        System.out.print("Стоимость: ");
        double price = readDouble();

        if (!validateBasePrice(price)) return;

        PizzaBase base = new PizzaBase(name, price, false);
        store.addBase(base);
        System.out.println("Добавлено: " + base.getFullInfo());
    }

    private boolean validateBasePrice(double price) {
        Optional<PizzaBase> classicOpt = store.getClassicBase();
        if (classicOpt.isPresent()) {
            double classicPrice = classicOpt.get().getPrice();
            double maxPrice = classicPrice * 1.2;
            if (price > maxPrice) {
                System.out.printf("Цена неклассической основы не должна превышать 120%% от классической (макс. %.2f руб.)", maxPrice);
                return false;
            }
        }
        return true;
    }

    private void editBase() {
        List<PizzaBase> list = store.getBases();
        if (list.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        PizzaBase base = selectFromList(list, "Выберите основу");
        if (base == null) {
            return;
        }
        if (base.isClassicBase()) {
            System.out.println("Нельзя редактировать классическую основу.");
            return;
        }

        System.out.print("Новое название (Enter — оставить): ");
        String name = readLine();
        if (!name.isEmpty()) {
            base.setName(name);
        }

        System.out.print("Новая стоимость (Enter — оставить): ");
        String priceStr = readLine();
        if (!priceStr.isEmpty()) {
            try {
                double price = Double.parseDouble(priceStr.replace(',', '.'));
                if (!validateBasePrice(price)) {
                    return;
                } if (price >= 0) {
                    base.setPrice(price);
                } else {
                    System.out.println("Стоимость не может быть отрицательной.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат.");
            }
        }
        System.out.println("Обновлено: " + base.getFullInfo());
    }

    private void deleteBase() {
        List<PizzaBase> list = store.getBases();
        if (list.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        PizzaBase base = selectFromList(list, "Выберите основу для удаления");
        if (base == null) {
            return;
        }
        if (base.isClassicBase()) {
            System.out.println("Нельзя удалять классическую основу");
            return;
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Введите целое число: ");
            }
        }
    }

    private String readLine() {
        return scanner.nextLine().trim();
    }

    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Введите число: ");
            }
        }
    }

    private <T extends Identifiable & Displayable> T selectFromList(List<T> items, String prompt) {
        System.out.println(prompt + ":");
        for (int i = 0; i < items.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + items.get(i).getDisplayName()
                    + (items.get(i) instanceof Priceable
                    ? String.format(" (%.2f руб.)", ((Priceable) items.get(i)).getPrice())
                    : ""));
        }
        System.out.print("Номер (0 — отмена): ");
        int idx = readInt() - 1;
        if (idx < 0 || idx >= items.size()) {
            if (idx != -1) System.out.println("Неверный номер.");
            return null;
        }
        return items.get(idx);
    }
}