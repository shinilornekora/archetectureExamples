package shiniasse.arch.domain;

import java.util.List;
import java.util.Optional;

public interface IInventoryRepository {
    void addInventory(Inventory inventory);
    Optional<Inventory> findInventoryByName(String name);
    List<Inventory> findAllInventories();
}