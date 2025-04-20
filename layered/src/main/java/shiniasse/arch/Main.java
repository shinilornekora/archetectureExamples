package shiniasse.arch;

import shiniasse.arch.application.InventoryService;
import shiniasse.arch.domain.IInventoryRepository;
import shiniasse.arch.infrastructure.InventoryRepository;
import shiniasse.arch.presentation.CLIShowcase;

public class Main {
    public static void main(String[] args) {
        // Создание инфраструктурного слоя (репозитории)
        IInventoryRepository inventoryRepository = new InventoryRepository();

        // Создание слоя приложения (сервис)
        InventoryService inventoryService = new InventoryService(inventoryRepository);

        // Добавление тестовых данных
        // Продукты для тестового инвентаря добавились автоматически
        final String InventoryName = "Складская 1";
        inventoryService.addTestInventory(InventoryName);

        // Создание слоя представления (UI)
        CLIShowcase ui = new CLIShowcase(inventoryService);

        // Запуск приложения
        ui.start();
    }
}