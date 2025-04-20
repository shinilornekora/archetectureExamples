package shiniasse.arch.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import shiniasse.arch.domain.IInventoryRepository;
import shiniasse.arch.domain.Inventory;

public class InventoryRepository implements IInventoryRepository {
    private final Map<String, Inventory> inventories = new HashMap<>();

    @Override
    public void addInventory(Inventory inventory) {
        this.inventories.put(inventory.getName(), inventory);
    }
    
    @Override
    public Optional<Inventory> findInventoryByName(String name) {
        return Optional.ofNullable(inventories.get(name));
    }

    @Override
    public List<Inventory> findAllInventories() {
        return new ArrayList<>(inventories.values());
    }
}
