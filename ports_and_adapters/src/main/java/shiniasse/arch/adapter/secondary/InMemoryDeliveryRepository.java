package shiniasse.arch.adapter.secondary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import shiniasse.arch.domain.model.Delivery.Delivery;
import shiniasse.arch.domain.port.secondary.DeliveryRepository;

public class InMemoryDeliveryRepository implements DeliveryRepository {
    private final Map<String, Delivery> deliveries = new HashMap<>();
    
    @Override
    public void save(Delivery order) {
        deliveries.put(order.getId(), order);
    }

    @Override
    public Optional<Delivery> findById(String id) {
        return Optional.ofNullable(deliveries.get(id));
    }

    @Override
    public List<Delivery> findAll() {
        return new ArrayList<>(deliveries.values());
    }
    
}
