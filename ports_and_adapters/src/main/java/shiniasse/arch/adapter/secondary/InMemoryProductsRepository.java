package shiniasse.arch.adapter.secondary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import shiniasse.arch.domain.model.Delivery.RealProductItem;
import shiniasse.arch.domain.port.secondary.ProductsRepository;

public class InMemoryProductsRepository implements ProductsRepository {
        private final Map<String, RealProductItem> realProductItems = new HashMap<>();

    @Override
    public void save(RealProductItem realProductItem) {
        this.realProductItems.put(realProductItem.getId(), realProductItem);
    }

    @Override
    public Optional<RealProductItem> findById(String id) {
        return Optional.ofNullable(realProductItems.get(id));
    }

    @Override
    public List<RealProductItem> findAll() {
        return new ArrayList<>(realProductItems.values());
    }
}
