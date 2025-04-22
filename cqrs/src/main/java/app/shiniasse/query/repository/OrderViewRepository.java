package app.shiniasse.query.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.shiniasse.common.exception.OrderNotFoundException;
import app.shiniasse.query.model.OrderView;

public class OrderViewRepository {
    private final Map<String, OrderView> store = new HashMap<>();

    public void save(OrderView v) { store.put(v.getOrderId(), v); }
    public OrderView findById(String id) {
        OrderView v = store.get(id);
        if (v == null) throw new OrderNotFoundException("Не найден OrderView: " + id);
        return v;
    }
    public List<OrderView> findAll() {
        return new ArrayList<>(store.values());
    }
}
