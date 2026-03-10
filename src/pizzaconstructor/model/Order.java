package pizzaconstructor.model;

import pizzaconstructor.interfaces.Displayable;
import pizzaconstructor.interfaces.Identifiable;
import pizzaconstructor.interfaces.Priceable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class Order implements Identifiable, Priceable, Displayable{
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static int counter = 0;

    private final UUID id;
    private final int orderNumber;
    private final List<OrderItem> items;
    private final String comment;
    private final LocalDateTime orderTime;
    private final LocalDateTime scheduledTime;

    public Order(List<OrderItem> items, String comment, LocalDateTime scheduledTime) {
        this.id = UUID.randomUUID();
        this.orderNumber = ++counter;
        this.items = new ArrayList<>(items);
        this.comment = comment;
        this.orderTime = LocalDateTime.now();
        this.scheduledTime = scheduledTime;
    }

    @Override
    public String getDisplayName() {
        return "Заказ #" + orderNumber;
    }

    @Override
    public String getFullInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("--- Заказ #%d --- [%s]\n", orderNumber, id));
        sb.append("  Время: ").append(orderTime.format(FMT)).append("\n");
        if (scheduledTime != null) {
            sb.append("  Отложено до: ").append(scheduledTime.format(FMT)).append("\n");
        }
        sb.append("  Комментарий: ").append(comment != null && !comment.isEmpty() ? comment : "-").append("\n");
        sb.append("  Позиции:\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(items.get(i).getFullInfo());
        }
        sb.append(String.format("  ИТОГО: %.2f руб.\n", getPrice()));
        return sb.toString();
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public double getPrice() {
        return items.stream().mapToDouble(i -> i.getPrice()).sum();
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }
}