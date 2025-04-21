package shiniasse.arch.domain.port.secondary;

import java.util.List;
import java.util.Optional;

import shiniasse.arch.domain.model.SupplyOrder.SupplyOrder;

public interface SupplyOrderRepository {
    void save(SupplyOrder order);
    Optional<SupplyOrder> findById(String id);
    List<SupplyOrder> findAll();
}
