package app.shiniasse.query.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderItemView {
    private final String id;
    private final String orderId;
    private final String dishId;
    private final String dishName;
    private final int quantity;
    private boolean prepared;
    private final LocalDateTime timestamp;

    public OrderItemView(String orderId, String dishId, String dishName, int qty, LocalDateTime ts) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = qty;
        this.prepared = false;
        this.timestamp = ts;
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public String getDishName() { return dishName; }
    public int getQuantity() { return quantity; }
    public boolean isPrepared() { return prepared; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void markPrepared() { this.prepared = true; }
}
