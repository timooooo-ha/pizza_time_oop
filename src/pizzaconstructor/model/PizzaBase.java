package pizzaconstructor.model;

public class PizzaBase {
    private String name;
    private double price;
    private boolean classicBase;

    public PizzaBase(String name, double price, boolean classicBase) {
        this.name = name;
        this.price = price;
        this.classicBase = classicBase;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getFullInfo() {
        String type = classicBase ? " [классическая]" : "";
        return String.format("%s - %.2f руб.%s", name, price, type);
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

    public void setClassicBase(boolean classicBase) {
        this.classicBase = classicBase;
    }

    public String toString() {
        return getFullInfo();
    }
}