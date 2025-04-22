package app.shiniasse.command.handler;

import app.shiniasse.command.command.CompleteCustomerOrderCommand;
import app.shiniasse.command.model.CustomerOrder;
import app.shiniasse.command.repository.CustomerOrderRepository;

public class CompleteCustomerOrderCommandHandler
        implements CommandHandler<CompleteCustomerOrderCommand> {

    private final CustomerOrderRepository repo;

    public CompleteCustomerOrderCommandHandler(CustomerOrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(CompleteCustomerOrderCommand cmd) {
        CustomerOrder order = repo.findById(cmd.getOrderId());
        order.completeOrder();
        repo.save(order);
    }
}
