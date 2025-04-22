package app.shiniasse.query.service;

import java.util.List;
import java.util.stream.Collectors;

import app.shiniasse.query.dto.OrderDTO;
import app.shiniasse.query.dto.OrderItemDTO;
import app.shiniasse.query.dto.OrderStatisticsDTO;
import app.shiniasse.query.model.OrderItemView;
import app.shiniasse.query.model.OrderView;
import app.shiniasse.query.repository.OrderItemViewRepository;
import app.shiniasse.query.repository.OrderViewRepository;

public class OrderQueryService {
    private final OrderViewRepository orderRepo;
    private final OrderItemViewRepository itemRepo;

    public OrderQueryService(OrderViewRepository orderRepo,
                             OrderItemViewRepository itemRepo) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
    }

    public OrderDTO getOrderById(String id) {
        OrderView v = orderRepo.findById(id);
        return new OrderDTO(
                v.getOrderId(),
                v.getStatus().name(),
                v.getCreatedAt(),
                v.getLastUpdatedAt(),
                v.getItemCount()
        );
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepo.findAll().stream()
                .map(v -> new OrderDTO(
                        v.getOrderId(),
                        v.getStatus().name(),
                        v.getCreatedAt(),
                        v.getLastUpdatedAt(),
                        v.getItemCount()
                ))
                .collect(Collectors.toList());
    }

    public List<OrderItemDTO> getOrderItems(String orderId) {
        return itemRepo.findByOrderId(orderId).stream()
                .map(iv -> new OrderItemDTO(
                        iv.getDishId(),
                        iv.getDishName(),
                        iv.getQuantity(),
                        iv.isPrepared(),
                        iv.getTimestamp()
                ))
                .collect(Collectors.toList());
    }

    public OrderStatisticsDTO getOrderStatistics(String orderId) {
        List<OrderItemView> items = itemRepo.findByOrderId(orderId);
        int total = items.size();
        int prepared = (int) items.stream().filter(OrderItemView::isPrepared).count();
        return new OrderStatisticsDTO(total, prepared, total - prepared);
    }
}
