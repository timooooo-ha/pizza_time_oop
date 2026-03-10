package pizzaconstructor.comparator;

import pizzaconstructor.interfaces.Displayable;

import java.util.Comparator;

public class NameComparator implements Comparator<Displayable> {
    private final boolean ascending;

    public NameComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Displayable a, Displayable b) {
        int result = a.getDisplayName().compareToIgnoreCase(b.getDisplayName());
        return ascending ? result : -result;
    }
}