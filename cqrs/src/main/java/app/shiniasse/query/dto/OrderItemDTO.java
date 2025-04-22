package app.shiniasse.query.dto;

import java.time.LocalDateTime;

public class OrderItemDTO {
    private final String dishId;
    private final String dishName;
    private final int quantity;
    private final boolean prepared;
    private final LocalDateTime timestamp;

    public OrderItemDTO(String dishId, String dishName, int quantity,
                        boolean prepared, LocalDateTime timestamp) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
        this.prepared = prepared;
        this.timestamp = timestamp;
    }

    public String getDishId() { return dishId; }
    public String getDishName() { return dishName; }
    public int getQuantity() { return quantity; }
    public boolean isPrepared() { return prepared; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
