package app.shiniasse.query.model;

import java.time.LocalDateTime;

public class OrderView {
    public enum Status { CREATED, PREPARING, COMPLETED }

    private final String orderId;
    private Status status;
    private final LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private int itemCount;

    public OrderView(String orderId, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.status = Status.CREATED;
        this.createdAt = createdAt;
        this.lastUpdatedAt = createdAt;
        this.itemCount = 0;
    }

    public String getOrderId() { return orderId; }
    public Status getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastUpdatedAt() { return lastUpdatedAt; }
    public int getItemCount() { return itemCount; }

    public void setStatus(Status s) {
        this.status = s;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void incrementItems(int delta) {
        this.itemCount += delta;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void decrementItems(int delta) {
        this.itemCount -= delta;
        this.lastUpdatedAt = LocalDateTime.now();
    }
}
