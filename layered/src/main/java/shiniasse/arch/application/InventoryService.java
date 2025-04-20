package shiniasse.arch.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import shiniasse.arch.domain.IInventoryRepository;
import shiniasse.arch.domain.Inventory;
import shiniasse.arch.domain.Product;
import shiniasse.arch.domain.TemperatureMode;

public class InventoryService {
    private final IInventoryRepository inventoryRepository;

    public InventoryService(IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void addNewProducts(Map<String, Product> products, String name) {
        final Inventory inventory = inventoryRepository.findInventoryByName(name).orElseThrow();

        inventory.addProducts(products);
    }

    // Вроде сущность рецепта не нужна.
    // Пусть нам просто извне передают список продуктов
    // Мы сами идем, находим, корректируем количество на складе.
    public void cookDish(Map<String, Product> products, String name) {
        final Inventory inventory = inventoryRepository.findInventoryByName(name).orElseThrow();

        inventory.removeProducts(products);
    }

    // Списание просроченных продуктов
    public void removeOutdatedProducts(String name) {
        final Inventory inventory = inventoryRepository.findInventoryByName(name).orElseThrow();

        // Будем считать что тогда вся партия продуктов просрочена.
        inventory.removeOutdatedProducts();
    }

    // Проведение инвентаризации и корректировка запасов
    // То есть, смотрим что у нас есть и дополняем до оптимального количества что надо.
    public String makeProductsInventorizationWithAutoCorrection(String name) {
        final Inventory inventory = inventoryRepository.findInventoryByName(name).orElseThrow();

        return inventory.makeProductsInventorizationWithAutoCorrection();
    }

    // Генерация отчетов о текущих запасах
    public String makeCurrentProductsDocs(String name) {
        final Inventory inventory = inventoryRepository.findInventoryByName(name).orElseThrow();

        return inventory.printDocumentation();
    }

    // Отслеживание продуктов с критическим уровнем запасов
    public String getCritThresholdOfProductAmount(String name) {
        final Inventory inventory = inventoryRepository.findInventoryByName(name).orElseThrow();
        
        return inventory.getCritThresholdProducts();
    }

    public void addTestInventory(String name) {
        final Map<String, Product> fakeProductsData = makeTestProductsData();
        Inventory inventory = new Inventory(name, fakeProductsData);

        this.inventoryRepository.addInventory(inventory);
    }

    private Map<String, Product> makeTestProductsData() {
        final Map<String, Product> products = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime expiryDateTime = LocalDateTime.parse("2024-03-15 11:15", formatter);

        final Product product_1 = new Product(
            "Перловка",
            15,
            expiryDateTime,
            TemperatureMode.FROZEN,
            5,
            15
        );

        final Product product_2 = new Product(
            "Гречка",
            5,
            expiryDateTime,
            TemperatureMode.FROZEN,
            1,
            5
        );

        final Product product_3 = new Product(
            "Манка",
            7,
            expiryDateTime,
            TemperatureMode.FROZEN,
            4,
            7
        );

        products.put(product_1.getName(), product_1);
        products.put(product_2.getName(), product_2);
        products.put(product_3.getName(), product_3);

        return products;
    }
}
