package app.shiniasse.query.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import app.shiniasse.query.model.OrderItemView;

public class OrderItemViewRepository {
    private final Map<String, OrderItemView> store = new HashMap<>();

    public void save(OrderItemView v) { store.put(v.getId(), v); }
    public List<OrderItemView> findByOrderId(String orderId) {
        return store.values().stream()
                .filter(i -> i.getOrderId().equals(orderId))
                .collect(Collectors.toList());
    }
    public void deleteByOrderAndDish(String orderId, String dishId) {
        List<String> toRemove = store.values().stream()
                .filter(i -> i.getOrderId().equals(orderId) && i.getDishId().equals(dishId))
                .map(OrderItemView::getId)
                .collect(Collectors.toList());
        toRemove.forEach(store::remove);
    }
}
