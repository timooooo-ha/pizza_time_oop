package pizzaconstructor.model;

public enum PizzaSize {
    SMALL("Маленькая", 0.8),
    MEDIUM("Средняя", 1.0),
    LARGE("Большая", 1.2);

    private final String displayName;
    private final double multiplier;

    PizzaSize(String displayName, double multiplier) {
        this.displayName = displayName;
        this.multiplier = multiplier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getMultiplier() {
        return multiplier;
    }
}