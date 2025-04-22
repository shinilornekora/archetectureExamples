package app.shiniasse.api.facade;

import java.util.List;

import app.shiniasse.command.command.*;
import app.shiniasse.command.handler.CommandBus;
import app.shiniasse.query.dto.OrderDTO;
import app.shiniasse.query.dto.OrderItemDTO;
import app.shiniasse.query.dto.OrderStatisticsDTO;
import app.shiniasse.query.service.OrderQueryService;

public class OrderFacade {
    private final CommandBus bus;
    private final OrderQueryService query;

    public OrderFacade(CommandBus bus, OrderQueryService query) {
        this.bus = bus;
        this.query = query;
    }

    // --- Command side ---
    public void createOrder() {
        bus.dispatch(new CreateCustomerOrderCommand());
    }

    public void addDish(String orderId, String dishId, String name, int qty) {
        bus.dispatch(new AddDishToOrderCommand(orderId, dishId, name, qty));
    }

    public void removeDish(String orderId, String dishId) {
        bus.dispatch(new RemoveDishFromOrderCommand(orderId, dishId));
    }

    public void prepareDish(String orderId, String dishId) {
        bus.dispatch(new PrepareDishCommand(orderId, dishId));
    }

    public void completeOrder(String orderId) {
        bus.dispatch(new CompleteCustomerOrderCommand(orderId));
    }

    // --- Query side ---
    public OrderDTO getOrder(String orderId) {
        return query.getOrderById(orderId);
    }

    public List<OrderDTO> getAllOrders() {
        return query.getAllOrders();
    }

    public List<OrderItemDTO> getOrderItems(String orderId) {
        return query.getOrderItems(orderId);
    }

    public OrderStatisticsDTO getOrderStatistics(String orderId) {
        return query.getOrderStatistics(orderId);
    }
}
