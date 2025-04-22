package app.shiniasse.query.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private final String orderId;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastUpdatedAt;
    private final int itemCount;

    public OrderDTO(String orderId, String status, LocalDateTime createdAt,
                    LocalDateTime lastUpdatedAt, int itemCount) {
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.itemCount = itemCount;
    }

    public String getOrderId() { return orderId; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastUpdatedAt() { return lastUpdatedAt; }
    public int getItemCount() { return itemCount; }
}
