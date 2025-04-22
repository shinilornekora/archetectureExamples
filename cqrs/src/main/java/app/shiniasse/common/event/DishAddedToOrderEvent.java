package app.shiniasse.common.event;

import java.time.LocalDateTime;

public class DishAddedToOrderEvent extends Event {
    private final String orderId;
    private final String dishId;
    private final String dishName;
    private final int quantity;
    private final LocalDateTime timestamp;

    public DishAddedToOrderEvent(String orderId, String dishId, String dishName, int quantity, LocalDateTime ts) {
        super();
        this.orderId = orderId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
        this.timestamp = ts;
    }

    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public String getDishName() { return dishName; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
