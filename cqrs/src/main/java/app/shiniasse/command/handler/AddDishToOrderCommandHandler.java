package app.shiniasse.command.handler;

import app.shiniasse.command.command.AddDishToOrderCommand;
import app.shiniasse.command.model.CustomerOrder;
import app.shiniasse.command.repository.CustomerOrderRepository;

public class AddDishToOrderCommandHandler
        implements CommandHandler<AddDishToOrderCommand> {

    private final CustomerOrderRepository repo;

    public AddDishToOrderCommandHandler(CustomerOrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(AddDishToOrderCommand cmd) {
        CustomerOrder order = repo.findById(cmd.getOrderId());
        order.addDish(cmd.getDishId(), cmd.getDishName(), cmd.getQuantity());
        repo.save(order);
    }
}
