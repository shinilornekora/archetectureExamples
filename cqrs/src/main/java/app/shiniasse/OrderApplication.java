package app.shiniasse;

import app.shiniasse.api.console.ConsoleInterface;
import app.shiniasse.api.facade.OrderFacade;
import app.shiniasse.command.command.*;
import app.shiniasse.command.handler.*;
import app.shiniasse.command.repository.CustomerOrderRepository;
import app.shiniasse.common.event.EventBus;
import app.shiniasse.query.repository.OrderItemViewRepository;
import app.shiniasse.query.repository.OrderViewRepository;
import app.shiniasse.query.service.EventHandler;
import app.shiniasse.query.service.OrderQueryService;

public class OrderApplication {
    public static void main(String[] args) {
        CustomerOrderRepository cmdRepo = new CustomerOrderRepository();

        OrderViewRepository viewRepo = new OrderViewRepository();
        OrderItemViewRepository itemRepo = new OrderItemViewRepository();

        EventHandler qHandler = new EventHandler(viewRepo, itemRepo);
        EventBus.getInstance().register(qHandler);

        CommandBus bus = new CommandBus();
        bus.register(CreateCustomerOrderCommand.class, new CreateCustomerOrderCommandHandler(cmdRepo));
        bus.register(AddDishToOrderCommand.class,     new AddDishToOrderCommandHandler(cmdRepo));
        bus.register(RemoveDishFromOrderCommand.class, new RemoveDishFromOrderCommandHandler(cmdRepo));
        bus.register(PrepareDishCommand.class,         new PrepareDishCommandHandler(cmdRepo));
        bus.register(CompleteCustomerOrderCommand.class, new CompleteCustomerOrderCommandHandler(cmdRepo));

        OrderQueryService queryService = new OrderQueryService(viewRepo, itemRepo);

        OrderFacade facade = new OrderFacade(bus, queryService);

        ConsoleInterface ui = new ConsoleInterface(facade);
        ui.start();
    }
}
