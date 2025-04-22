package app.shiniasse.api.console;

import java.util.List;
import java.util.Scanner;

import app.shiniasse.api.facade.OrderFacade;
import app.shiniasse.common.exception.InvalidOrderOperationException;
import app.shiniasse.common.exception.OrderNotFoundException;
import app.shiniasse.query.dto.OrderDTO;
import app.shiniasse.query.dto.OrderItemDTO;
import app.shiniasse.query.dto.OrderStatisticsDTO;

public class ConsoleInterface {
    private final OrderFacade facade;
    private final Scanner scanner;

    public ConsoleInterface(OrderFacade facade) {
        this.facade = facade;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = readInt();
            try {
                switch (choice) {
                    case 0: System.out.println("Выход."); break;
                    case 1: createOrder(); break;
                    case 2: addDish(); break;
                    case 3: removeDish(); break;
                    case 4: prepareDish(); break;
                    case 5: completeOrder(); break;
                    case 6: showOrderDetails(); break;
                    case 7: showAllOrders(); break;
                    case 8: showOrderItems(); break;
                    case 9: showStatistics(); break;
                    default: System.out.println("Неверный выбор.");
                }
            } catch (OrderNotFoundException | InvalidOrderOperationException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n=== Меню управления заказами ===");
        System.out.println("1. Создать новый заказ");
        System.out.println("2. Добавить блюдо в заказ");
        System.out.println("3. Удалить блюдо из заказа");
        System.out.println("4. Отметить блюдо приготовленным");
        System.out.println("5. Завершить заказ");
        System.out.println("6. Показать детали заказа");
        System.out.println("7. Показать все заказы");
        System.out.println("8. Показать блюда заказа");
        System.out.println("9. Статистика заказа");
        System.out.println("0. Выход");
        System.out.print("Выберите: ");
    }

    private void createOrder() {
        facade.createOrder();
        System.out.println("Новый заказ создан.");
    }

    private void addDish() {
        System.out.print("ID заказа: ");
        String orderId = scanner.nextLine().trim();
        System.out.print("ID блюда: ");
        String dishId = scanner.nextLine().trim();
        System.out.print("Название блюда: ");
        String name = scanner.nextLine().trim();
        System.out.print("Количество: ");
        int qty = readInt();
        facade.addDish(orderId, dishId, name, qty);
        System.out.println("Блюдо добавлено.");
    }

    private void removeDish() {
        System.out.print("ID заказа: ");
        String orderId = scanner.nextLine().trim();
        System.out.print("ID блюда: ");
        String dishId = scanner.nextLine().trim();
        facade.removeDish(orderId, dishId);
        System.out.println("Блюдо удалено.");
    }

    private void prepareDish() {
        System.out.print("ID заказа: ");
        String orderId = scanner.nextLine().trim();
        System.out.print("ID блюда: ");
        String dishId = scanner.nextLine().trim();
        facade.prepareDish(orderId, dishId);
        System.out.println("Блюдо отмечено как приготовленное.");
    }

    private void completeOrder() {
        System.out.print("ID заказа: ");
        String orderId = scanner.nextLine().trim();
        facade.completeOrder(orderId);
        System.out.println("Заказ завершён.");
    }

    private void showOrderDetails() {
        System.out.print("ID заказа: ");
        String orderId = scanner.nextLine().trim();
        OrderDTO dto = facade.getOrder(orderId);
        System.out.println("\n--- Детали заказа ---");
        System.out.println("ID: " + dto.getOrderId());
        System.out.println("Статус: " + dto.getStatus());
        System.out.println("Создан: " + dto.getCreatedAt());
        System.out.println("Обновлён: " + dto.getLastUpdatedAt());
    }

    private void showAllOrders() {
        List<OrderDTO> list = facade.getAllOrders();
        if (list.isEmpty()) {
            System.out.println("Нет заказов.");
            return;
        }
        System.out.println("\n--- Все заказы ---");
        for (OrderDTO o : list) {
            System.out.printf("ID: %s, Статус: %s, Блюд: %d%n",
                    o.getOrderId(), o.getStatus(), o.getItemCount());
        }
    }

    private void showOrderItems() {
        System.out.print("ID заказа: ");
        String orderId = scanner.nextLine().trim();
        List<OrderItemDTO> items = facade.getOrderItems(orderId);
        if (items.isEmpty()) {
            System.out.println("Блюд нет.");
            return;
        }
        System.out.println("\n--- Блюда в заказе ---");
        for (OrderItemDTO it : items) {
            System.out.printf("ID блюда: %s, Назв.: %s, Кол-во: %d, Приготивлено: %b%n",
                    it.getDishId(), it.getDishName(), it.getQuantity(), it.isPrepared());
        }
    }

    private void showStatistics() {
        System.out.print("ID заказа: ");
        String orderId = scanner.nextLine().trim();
        OrderStatisticsDTO st = facade.getOrderStatistics(orderId);
        System.out.println("\n--- Статистика заказа ---");
        System.out.println("Всего позиций: " + st.getTotalItems());
        System.out.println("Приготовлено: " + st.getPreparedCount());
        System.out.println("Ожидают приготовления: " + st.getUnpreparedCount());
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
