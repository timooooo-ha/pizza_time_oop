package pizzaconstructor;

import pizzaconstructor.service.DataStore;
import pizzaconstructor.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        DataStore.getInstance().initSampleData();
        new ConsoleUI().mainMenu();
    }
}