package shiniasse.arch.domain.model.Delivery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Delivery {
    private final String id;
    private final String supplyOrderId;
    private final List<RealProductItem> realProductItems;

    public Delivery(String id, String supplyOrderId, List<RealProductItem> realProductItems) {
        this.id = id;
        this.supplyOrderId = supplyOrderId;
        this.realProductItems = realProductItems;
    }

    public String getId() {
        return id;
    }

    public String getSupplyOrderId() {
        return supplyOrderId;
    }

    public List<RealProductItem> getRealProductItems() {
        return realProductItems;
    }

    public boolean checkProductsQuality() {
        LocalDateTime now = LocalDateTime.now();
        for (RealProductItem item : realProductItems) {
            if (item.getExpirationDateTime().isBefore(now)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return (
            "ID продукта - " + this.getId() + "\n" +
            "ID заказа - " + this.getSupplyOrderId() + "\n" +
            "-- Продукты в доставке -- \n" + this.getRealProductItems().stream()
                .map(RealProductItem::toString).collect(Collectors.joining("\n")) + "\n" 
        );
    }
}
