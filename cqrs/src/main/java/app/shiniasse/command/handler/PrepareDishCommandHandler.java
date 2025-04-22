package app.shiniasse.command.handler;

import app.shiniasse.command.command.PrepareDishCommand;
import app.shiniasse.command.model.CustomerOrder;
import app.shiniasse.command.repository.CustomerOrderRepository;

public class PrepareDishCommandHandler
        implements CommandHandler<PrepareDishCommand> {

    private final CustomerOrderRepository repo;

    public PrepareDishCommandHandler(CustomerOrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(PrepareDishCommand cmd) {
        CustomerOrder order = repo.findById(cmd.getOrderId());
        order.prepareDish(cmd.getDishId());
        repo.save(order);
    }
}
