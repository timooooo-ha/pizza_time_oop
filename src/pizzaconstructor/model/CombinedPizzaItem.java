package pizzaconstructor.model;

public class CombinedPizzaItem extends OrderItem {
    private final Pizza firstHalf;
    private final Pizza secondHalf;
    private final PizzaBase base;
    private final Crust firstHalfCrust;
    private final Crust secondHalfCrust;

    public CombinedPizzaItem(Pizza firstHalf, Pizza secondHalf, PizzaBase base,
                             PizzaSize size, Crust sharedCrust,
                             Crust firstHalfCrust, Crust secondHalfCrust) {
        super(size, sharedCrust);
        this.firstHalf = firstHalf;
        this.secondHalf = secondHalf;
        this.base = base;
        this.firstHalfCrust = firstHalfCrust;
        this.secondHalfCrust = secondHalfCrust;
    }

    @Override
    public double getPrice() {
        double total = base.getPrice();
        total += firstHalf.getIngredientsPrice() / 2.0;
        total += secondHalf.getIngredientsPrice() / 2.0;
        total += getCrustPrice();
        if (firstHalfCrust != null) {
            total += firstHalfCrust.getPrice() / 2.0;
        }
        if (secondHalfCrust != null) {
            total += secondHalfCrust.getPrice() / 2.0;
        }
        return total * getSize().getMultiplier();
    }

    @Override
    public String getDisplayName() {
        return firstHalf.getDisplayName() + " + " + secondHalf.getDisplayName()
                + " (" + getSize().getDisplayName() + ")";
    }

    @Override
    public String getFullInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("  Комбо: %s + %s | Размер: %s | Цена: %.2f руб.\n",
                firstHalf.getDisplayName(), secondHalf.getDisplayName(),
                getSize().getDisplayName(), getPrice()));
        sb.append("    Общая основа: ").append(base.getDisplayName()).append("\n");
        if (getCrust() != null) {
            sb.append("    Общий бортик: ").append(getCrust().getDisplayName()).append("\n");
        }
        if (firstHalfCrust != null) {
            sb.append("    Бортик 1-й половины: ").append(firstHalfCrust.getDisplayName()).append("\n");
        }
        if (secondHalfCrust != null) {
            sb.append("    Бортик 2-й половины: ").append(secondHalfCrust.getDisplayName()).append("\n");
        }
        return sb.toString();
    }

    public Pizza getFirstHalf() {
        return firstHalf;
    }

    public Pizza getSecondHalf() {
        return secondHalf;
    }

    public PizzaBase getBase() {
        return base;
    }

    public Crust getFirstHalfCrust() {
        return firstHalfCrust;
    }

    public Crust getSecondHalfCrust() {
        return secondHalfCrust;
    }

    @Override
    public String toString() {
        return getFullInfo();
    }
}
