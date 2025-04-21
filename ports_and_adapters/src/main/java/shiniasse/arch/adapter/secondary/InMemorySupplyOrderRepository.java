package shiniasse.arch.adapter.secondary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import shiniasse.arch.domain.model.SupplyOrder.SupplyOrder;
import shiniasse.arch.domain.port.secondary.SupplyOrderRepository;

public class InMemorySupplyOrderRepository implements SupplyOrderRepository {
    private final Map<String, SupplyOrder> supplyOrders = new HashMap<>();

    @Override
    public void save(SupplyOrder order) {
        this.supplyOrders.put(order.getId(), order);
    }

    @Override
    public Optional<SupplyOrder> findById(String id) {
        return Optional.ofNullable(supplyOrders.get(id));
    }

    @Override
    public List<SupplyOrder> findAll() {
        return new ArrayList<>(supplyOrders.values());
    }
}
