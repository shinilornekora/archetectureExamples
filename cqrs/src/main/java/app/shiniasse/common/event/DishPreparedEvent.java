package app.shiniasse.common.event;

import java.time.LocalDateTime;

public class DishPreparedEvent extends Event {
    private final String orderId;
    private final String dishId;
    private final LocalDateTime timestamp;

    public DishPreparedEvent(String orderId, String dishId, LocalDateTime ts) {
        super();
        this.orderId = orderId;
        this.dishId = dishId;
        this.timestamp = ts;
    }

    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
