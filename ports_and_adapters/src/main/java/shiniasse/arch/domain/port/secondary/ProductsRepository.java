package shiniasse.arch.domain.port.secondary;

import java.util.List;
import java.util.Optional;

import shiniasse.arch.domain.model.Delivery.RealProductItem;

public interface ProductsRepository {
    void save(RealProductItem realProductItem);
    Optional<RealProductItem> findById(String id);
    List<RealProductItem> findAll();
}
