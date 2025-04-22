package app.shiniasse.query.service;

import app.shiniasse.common.event.*;
import app.shiniasse.query.model.OrderItemView;
import app.shiniasse.query.model.OrderView;
import app.shiniasse.query.repository.OrderItemViewRepository;
import app.shiniasse.query.repository.OrderViewRepository;

public class EventHandler implements EventBus.EventHandler {
    private final OrderViewRepository orderRepo;
    private final OrderItemViewRepository itemRepo;

    public EventHandler(OrderViewRepository oRepo, OrderItemViewRepository iRepo) {
        this.orderRepo = oRepo;
        this.itemRepo = iRepo;
    }

    @Override
    public void handle(Event e) {
        if (e instanceof CustomerOrderPlacedEvent) {
            CustomerOrderPlacedEvent ev = (CustomerOrderPlacedEvent) e;
            OrderView view = new OrderView(ev.getOrderId(), ev.getCreatedAt());
            orderRepo.save(view);
        } else if (e instanceof DishAddedToOrderEvent) {
            DishAddedToOrderEvent ev = (DishAddedToOrderEvent) e;
            OrderItemView iv = new OrderItemView(
                    ev.getOrderId(),
                    ev.getDishId(),
                    ev.getDishName(),
                    ev.getQuantity(),
                    ev.getTimestamp()
            );
            itemRepo.save(iv);
            OrderView ov = orderRepo.findById(ev.getOrderId());
            ov.incrementItems(ev.getQuantity());
            orderRepo.save(ov);
        } else if (e instanceof DishRemovedFromOrderEvent) {
            DishRemovedFromOrderEvent ev = (DishRemovedFromOrderEvent) e;
            itemRepo.deleteByOrderAndDish(ev.getOrderId(), ev.getDishId());
            OrderView ov = orderRepo.findById(ev.getOrderId());
            ov.decrementItems(1);
            orderRepo.save(ov);
        } else if (e instanceof DishPreparedEvent) {
            DishPreparedEvent ev = (DishPreparedEvent) e;
            itemRepo.findByOrderId(ev.getOrderId())
                    .stream()
                    .filter(iv -> iv.getDishId().equals(ev.getDishId()))
                    .findFirst()
                    .ifPresent(iv -> {
                        iv.markPrepared();
                        itemRepo.save(iv);
                    });
        } else if (e instanceof CustomerOrderCompletedEvent) {
            CustomerOrderCompletedEvent ev = (CustomerOrderCompletedEvent) e;
            OrderView ov = orderRepo.findById(ev.getOrderId());
            ov.setStatus(OrderView.Status.COMPLETED);
            orderRepo.save(ov);
        }
    }
}
