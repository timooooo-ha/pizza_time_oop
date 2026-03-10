package pizzaconstructor.comparator;

import pizzaconstructor.interfaces.Priceable;

import java.util.Comparator;

public class PriceComparator implements Comparator<Priceable> {
    private final boolean ascending;

    public PriceComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Priceable a, Priceable b) {
        int result = Double.compare(a.getPrice(), b.getPrice());
        return ascending ? result : -result;
    }
}