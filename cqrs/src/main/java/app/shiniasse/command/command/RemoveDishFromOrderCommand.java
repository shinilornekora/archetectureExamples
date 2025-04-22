package app.shiniasse.command.command;

import java.util.UUID;

public class RemoveDishFromOrderCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final String dishId;

    public RemoveDishFromOrderCommand(String orderId, String dishId) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.dishId = dishId;
    }

    @Override
    public String getCommandId() { return commandId; }
    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
}
