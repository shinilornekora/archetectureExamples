package app.shiniasse.command.command;

import java.util.UUID;

public class AddDishToOrderCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final String dishId;
    private final String dishName;
    private final int quantity;

    public AddDishToOrderCommand(String orderId, String dishId, String dishName, int quantity) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
    }

    @Override
    public String getCommandId() { return commandId; }
    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public String getDishName() { return dishName; }
    public int getQuantity() { return quantity; }
}
