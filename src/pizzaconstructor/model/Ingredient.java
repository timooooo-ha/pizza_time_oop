package pizzaconstructor.model;

public class Ingredient {
    private String name;
    private double price;

    public Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getFullInfo() {
        return String.format("%s - %.2f руб.", name, price);
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
}