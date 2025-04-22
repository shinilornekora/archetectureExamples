package app.shiniasse.command.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.shiniasse.command.model.CustomerOrder;
import app.shiniasse.common.exception.OrderNotFoundException;

public class CustomerOrderRepository {
    private final Map<String, CustomerOrder> store = new HashMap<>();

    public void save(CustomerOrder order) {
        store.put(order.getId(), order);
    }

    public CustomerOrder findById(String id) {
        CustomerOrder o = store.get(id);
        if (o == null) throw new OrderNotFoundException("Заказ не найден: " + id);
        return o;
    }

    public List<CustomerOrder> findAll() {
        return new ArrayList<>(store.values());
    }
}
