package app.shiniasse.common.event;

import java.time.LocalDateTime;

public class CustomerOrderPlacedEvent extends Event {
    private final String orderId;
    private final LocalDateTime createdAt;

    public CustomerOrderPlacedEvent(String orderId, LocalDateTime createdAt) {
        super();
        this.orderId = orderId;
        this.createdAt = createdAt;
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
