package shiniasse.arch.presentation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import shiniasse.arch.application.InventoryService;
import shiniasse.arch.domain.Product;
import shiniasse.arch.domain.TemperatureMode;

public class CLIShowcase {
    private final InventoryService inventoryService;
    private final Scanner scanner;
    private final static String INV_NAME = "Складская 1";

    public CLIShowcase(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); 
            handleMenuChoice(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n===== Контроль запасов продуктов в ресторане =====");
        System.out.println("1. Добавление новых продуктов в инвентарь");
        System.out.println("2. Списание продуктов при использовании для приготовления блюд");
        System.out.println("3. Списание просроченных продуктов");
        System.out.println("4. Проведение инвентаризации и корректировка запасов");
        System.out.println("5. Генерация отчетов о текущих запасах\r");
        System.out.println("6. Отслеживание продуктов с критическим уровнем запасов\r");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> makePrettyAddingMenu();
            case 2 -> cookMenu();
            case 3 -> this.inventoryService.removeOutdatedProducts(INV_NAME);
            case 4 -> this.inventoryService.makeProductsInventorizationWithAutoCorrection(INV_NAME);
            case 5 -> this.inventoryService.makeCurrentProductsDocs(INV_NAME);
            case 6 -> this.inventoryService.getCritThresholdOfProductAmount(INV_NAME);
            case 0 -> System.out.println("Выход из программы...");
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void makePrettyAddingMenu() {
        // Можно вынести создание одного продукта отдельно, и тогда класть сюда сразу пачку продуктов
        Map<String, Product> products = new HashMap<>();

        System.out.println("Итак, новый продукт. Имя: ");
        String productName = scanner.nextLine();
        System.out.println("Оптимальное кол-во продукта: ");
        int optimalProductAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Критическое кол-во продукта: ");
        int critProductAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("""
                Температурный режим
                FROZEN - 0
                COLDIFIED - 1
                NORMAL - 2
                """);
        TemperatureMode temperatureMode = TemperatureMode.values()[Integer.parseInt(scanner.nextLine())];
        System.out.println("Срок годности (до) в формате yyyy-MM-dd HH:mm - ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime outProductDate = LocalDateTime.parse(scanner.nextLine(), formatter);

        Product product = new Product(
                productName,
                optimalProductAmount,
                outProductDate,
                temperatureMode,
                critProductAmount,
                optimalProductAmount
        );

        products.put(productName, product);
        inventoryService.addNewProducts(products, INV_NAME);

        System.out.println("Успешно добавлен продукт!");
    }

    private void cookMenu() {
        System.out.println("""
            Приготовим что-нибудь!
            
            1) Перловая каша
            2) Гречневая каша
            3) Манная каша

        """);

        int dishVariant = Integer.parseInt(scanner.nextLine());

        switch (dishVariant) {
            case 1 -> {}
            case 2 -> {}
            case 3 -> {}
            default -> System.out.println("Тогда не будем готовить!");
        }

    }
}