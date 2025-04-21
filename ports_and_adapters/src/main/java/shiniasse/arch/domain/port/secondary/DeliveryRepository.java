package shiniasse.arch.domain.port.secondary;

import java.util.List;
import java.util.Optional;

import shiniasse.arch.domain.model.Delivery.Delivery;

public interface DeliveryRepository {
    void save(Delivery order);
    Optional<Delivery> findById(String id);
    List<Delivery> findAll();
}
