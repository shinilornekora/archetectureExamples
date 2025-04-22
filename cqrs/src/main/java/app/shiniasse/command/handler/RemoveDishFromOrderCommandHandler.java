package app.shiniasse.command.handler;

import app.shiniasse.command.command.RemoveDishFromOrderCommand;
import app.shiniasse.command.model.CustomerOrder;
import app.shiniasse.command.repository.CustomerOrderRepository;

public class RemoveDishFromOrderCommandHandler
        implements CommandHandler<RemoveDishFromOrderCommand> {

    private final CustomerOrderRepository repo;

    public RemoveDishFromOrderCommandHandler(CustomerOrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(RemoveDishFromOrderCommand cmd) {
        CustomerOrder order = repo.findById(cmd.getOrderId());
        order.removeDish(cmd.getDishId());
        repo.save(order);
    }
}
