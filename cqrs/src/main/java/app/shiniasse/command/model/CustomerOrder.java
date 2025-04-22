package app.shiniasse.command.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import app.shiniasse.common.event.*;
import app.shiniasse.common.exception.InvalidOrderOperationException;

public class CustomerOrder {
    public enum Status { CREATED, PREPARING, COMPLETED }

    private final String id;
    private Status status;
    private final LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private final Map<String, OrderItem> items = new LinkedHashMap<>();

    public CustomerOrder() {
        this.id = UUID.randomUUID().toString();
        this.status = Status.CREATED;
        this.createdAt = LocalDateTime.now();
        this.lastUpdatedAt = createdAt;
        EventBus.getInstance().publish(new CustomerOrderPlacedEvent(id, createdAt));
    }

    // used by repo for rehydration
    public CustomerOrder(String id, Status st, LocalDateTime c, LocalDateTime u, Map<String, OrderItem> its) {
        this.id = id; this.status = st; this.createdAt = c; this.lastUpdatedAt = u;
        this.items.putAll(its);
    }

    public String getId() { return id; }
    public Status getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastUpdatedAt() { return lastUpdatedAt; }

    public void addDish(String dishId, String name, int qty) {
        if (status == Status.COMPLETED) throw new InvalidOrderOperationException("Нельзя добавлять блюдо в завершённый заказ");
        if (qty <= 0) throw new InvalidOrderOperationException("Количество должно быть > 0");
        items.compute(dishId, (k,v) -> {
            if (v == null) return new OrderItem(dishId, name, qty);
            v.increaseQuantity(qty);
            return v;
        });
        lastUpdatedAt = LocalDateTime.now();
        EventBus.getInstance().publish(new DishAddedToOrderEvent(id, dishId, name, qty, lastUpdatedAt));
    }

    public void removeDish(String dishId) {
        if (status == Status.COMPLETED) throw new InvalidOrderOperationException("Нельзя удалять блюдо из завершённого заказа");
        OrderItem removed = items.remove(dishId);
        if (removed == null) throw new InvalidOrderOperationException("Блюдо не найдено в заказе");
        lastUpdatedAt = LocalDateTime.now();
        EventBus.getInstance().publish(new DishRemovedFromOrderEvent(id, dishId, lastUpdatedAt));
    }

    public void prepareDish(String dishId) {
        OrderItem it = items.get(dishId);
        if (it == null) throw new InvalidOrderOperationException("Блюдо не найдено");
        if (it.isPrepared()) throw new InvalidOrderOperationException("Блюдо уже приготовлено");
        it.markPrepared();
        if (status == Status.CREATED) status = Status.PREPARING;
        lastUpdatedAt = LocalDateTime.now();
        EventBus.getInstance().publish(new DishPreparedEvent(id, dishId, lastUpdatedAt));
    }

    public void completeOrder() {
        if (status == Status.COMPLETED) throw new InvalidOrderOperationException("Заказ уже завершён");
        boolean allPrepared = items.values().stream().allMatch(OrderItem::isPrepared);
        if (!allPrepared) throw new InvalidOrderOperationException("Не все блюда приготовлены");
        status = Status.COMPLETED;
        lastUpdatedAt = LocalDateTime.now();
        EventBus.getInstance().publish(new CustomerOrderCompletedEvent(id, lastUpdatedAt));
    }

    public Map<String, OrderItem> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
