package shiniasse.arch.domain.model.SupplyOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SupplyOrder {
    private final List<ProductAbstractItem> supplyOrderItems;
    private final LocalDateTime createdAt;
    private SupplyOrderStatus supplyOrderStatus;

    private final String id;

    public SupplyOrder(
        List<ProductAbstractItem> supplyOrderItems,
        LocalDateTime createdAt,
        String id
    ) {
        this.supplyOrderItems = supplyOrderItems;
        this.supplyOrderStatus = SupplyOrderStatus.values()[0];
        this.createdAt = createdAt;
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ProductAbstractItem> getSupplyOrderItems() {
        return supplyOrderItems;
    }

    public String getId() {
        return id;
    }

    // К сожалению, Java не умеет итерироваться по перечислениям.
    // Ну или я делаю что-то не так.
    public void changeSupplyOrderStatus() {
        SupplyOrderStatus[] vSupplyOrderStatuses = SupplyOrderStatus.values();
        int currentIndex = this.supplyOrderStatus.ordinal();
        int nextIndex = (currentIndex + 1) % vSupplyOrderStatuses.length;
        this.supplyOrderStatus = vSupplyOrderStatuses[nextIndex];
    }

    public SupplyOrderStatus getSupplyOrderStatus() {
        return supplyOrderStatus;
    }

    @Override
    public String toString() {
        return (
            "ID заказа - " + this.getId() + "\n" +
            "Статус заказа - " + this.getSupplyOrderStatus() + "\n" +
            "Дата создания заказа - " + this.getCreatedAt().toString() + "\n" +
            "-- Продукты в заказе -- \n" + this.getSupplyOrderItems().stream()
                .map(ProductAbstractItem::toString).collect(Collectors.joining("\n")) + "\n" 
        );
    }
}
