package app.shiniasse.common.event;

import java.time.LocalDateTime;

public class CustomerOrderCompletedEvent extends Event {
    private final String orderId;
    private final LocalDateTime timestamp;

    public CustomerOrderCompletedEvent(String orderId, LocalDateTime ts) {
        super();
        this.orderId = orderId;
        this.timestamp = ts;
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
