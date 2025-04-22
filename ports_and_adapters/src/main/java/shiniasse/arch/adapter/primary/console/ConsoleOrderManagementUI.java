package shiniasse.arch.adapter.primary.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import shiniasse.arch.domain.model.SupplyOrder.ProductAbstractItem;
import shiniasse.arch.domain.port.primary.OrderProcessingSystem;

public class ConsoleOrderManagementUI {
    private final Scanner scanner;
    private final OrderProcessingSystem orderProcessingSystem;

    public ConsoleOrderManagementUI(OrderProcessingSystem orderProcessingSystem) {
        this.scanner = new Scanner(System.in);
        this.orderProcessingSystem = orderProcessingSystem;
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
        System.out.println("\n===== Управление процессом заказа продуктов =====");
        System.out.println("1. Создание заказа поставщику\r");
        System.out.println("2. Отправка заказа поставщику");
        System.out.println("3. Получение подтверждения от поставщика");
        System.out.println("4. Отслеживание статуса заказа");
        System.out.println("5. Приемка поставки и контроль качества\r");
        System.out.println("6. Просмотр всех заказов\r");
        System.out.println("7. Просмотр всех доставок\r");
        System.out.println("8. Просмотр всех доступных продуктов\r");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> handleOrderCreation();
            case 2 -> handleOrderSend();
            case 3 -> handleOrderConfirm();
            case 4 -> handleOrderStatusCheck();
            case 5 -> handleDeliveryProcessing();
            case 6 -> System.out.println(orderProcessingSystem.getAllSupplyOrders());
            case 7 -> System.out.println(orderProcessingSystem.getAllDeliveries());
            case 8 -> System.out.println(orderProcessingSystem.getAllProducts());
            case 0 -> System.out.println("Выход из программы...");
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void handleOrderCreation() {
        final List<ProductAbstractItem> productAbstractItems = new ArrayList<>();

        do {
            System.out.println("Итак, новый продукт, имя: ");
            final String name = scanner.nextLine();
            System.out.println("Количество продукта: ");
            final int amount = scanner.nextInt();
            System.out.println("Цена продукта: ");
            final int price = scanner.nextInt();

            // Очищаем буфер ввода
            scanner.nextLine();

            ProductAbstractItem productAbstractItem = new ProductAbstractItem(
                name,
                name, 
                price, 
                amount
            );

            productAbstractItems.add(productAbstractItem);

            System.out.println("Еще нужны продукты? (Y/N)");
            final String decision = scanner.nextLine();

            if (!"Y".equals(decision)) {
                break;
            }
        } while (true);

        this.orderProcessingSystem.createSupplyOrder(productAbstractItems);
    }

    private void handleOrderSend() {
        System.out.println("Введите айди заказа: ");
        final String supplyOrderId = scanner.nextLine();

        this.orderProcessingSystem.sendSupplyOrder(supplyOrderId);
    }

    private void handleOrderConfirm() {
        System.out.println("Введите айди заказа: ");
        final String supplyOrderId = scanner.nextLine();

        this.orderProcessingSystem.confirmOrder(supplyOrderId);
    }

    private void handleOrderStatusCheck() {
        System.out.println("Введите айди заказа: ");
        final String supplyOrderId = scanner.nextLine();

        System.out.println("Статус - " + this.orderProcessingSystem.getOrderStatus(supplyOrderId));
    }

    private void handleDeliveryProcessing() {
        System.out.println("Введите айди доставки: ");
        final String supplyOrderId = scanner.nextLine();

        this.orderProcessingSystem.getSuppliesWithQualityCheck(supplyOrderId);
    }
}
