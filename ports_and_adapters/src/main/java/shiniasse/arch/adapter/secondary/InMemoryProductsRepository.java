package shiniasse.arch.adapter.secondary;

import java.time.LocalDateTime;
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
        this.realProductItems.put(realProductItem.getName(), realProductItem);
    }

    @Override
    public Optional<RealProductItem> findById(String id) {
        return Optional.ofNullable(realProductItems.get(id));
    }

    @Override
    public List<RealProductItem> findAll() {
        return new ArrayList<>(realProductItems.values());
    }

    @Override
    public void populateWithTestData() {
        save(new RealProductItem(
                "1",
                "Milk",
                65.0,
                10.0,
                LocalDateTime.now().minusDays(5)
        ));
        save(new RealProductItem(
                "2",
                "Bread",
                40.0,
                15.0,
                LocalDateTime.now().plusDays(2)
        ));
        save(new RealProductItem(
                "3",
                "Cheese",
                320.0,
                5.0,
                LocalDateTime.now().plusDays(30)
        ));
        save(new RealProductItem(
                "4",
                "Apple",
                120.0,
                25.0,
                LocalDateTime.now().plusDays(10)
        ));
    }
}
