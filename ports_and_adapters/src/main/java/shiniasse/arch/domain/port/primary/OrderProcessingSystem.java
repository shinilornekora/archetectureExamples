package shiniasse.arch.domain.port.primary;

import java.util.List;

import shiniasse.arch.domain.model.Delivery.Delivery;
import shiniasse.arch.domain.model.SupplyOrder.ProductAbstractItem;
import shiniasse.arch.domain.model.SupplyOrder.SupplyOrderStatus;

public interface OrderProcessingSystem {
    public void createSupplyOrder(List<ProductAbstractItem> productsList);
    public void sendSupplyOrder(String supplyOrderId);
    public void confirmOrder(String supplyOrderId);
    public SupplyOrderStatus getOrderStatus(String supplyOrderId);
    public void getSuppliesWithQualityCheck(String deliveryId);

    // Вызывается только внутри.
    // К примеру, когда есть проблема с качеством.
    public void processSupplyOrderRejection(Delivery delivery);
}
