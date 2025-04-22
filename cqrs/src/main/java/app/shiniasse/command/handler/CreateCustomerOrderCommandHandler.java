package app.shiniasse.command.handler;

import app.shiniasse.command.command.CreateCustomerOrderCommand;
import app.shiniasse.command.model.CustomerOrder;
import app.shiniasse.command.repository.CustomerOrderRepository;

public class CreateCustomerOrderCommandHandler
        implements CommandHandler<CreateCustomerOrderCommand> {

    private final CustomerOrderRepository repo;

    public CreateCustomerOrderCommandHandler(CustomerOrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(CreateCustomerOrderCommand cmd) {
        CustomerOrder order = new CustomerOrder();
        repo.save(order);
    }
}
