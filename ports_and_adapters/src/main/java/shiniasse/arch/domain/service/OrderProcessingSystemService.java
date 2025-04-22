package shiniasse.arch.domain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import shiniasse.arch.domain.model.Delivery.Delivery;
import shiniasse.arch.domain.model.Delivery.RealProductItem;
import shiniasse.arch.domain.model.SupplyOrder.ProductAbstractItem;
import shiniasse.arch.domain.model.SupplyOrder.SupplyOrder;
import shiniasse.arch.domain.model.SupplyOrder.SupplyOrderStatus;
import shiniasse.arch.domain.port.primary.OrderProcessingSystem;
import shiniasse.arch.domain.port.secondary.DeliveryRepository;
import shiniasse.arch.domain.port.secondary.ProductsRepository;
import shiniasse.arch.domain.port.secondary.SupplyOrderRepository;

public class OrderProcessingSystemService implements OrderProcessingSystem {
    private final SupplyOrderRepository supplyOrderRepository;
    private final DeliveryRepository deliveryRepository;
    private final ProductsRepository productsRepository;

    public OrderProcessingSystemService(
        SupplyOrderRepository supplyOrderRepository,
        DeliveryRepository deliveryRepository,
        ProductsRepository productsRepository
    ) {
        this.supplyOrderRepository = supplyOrderRepository;
        this.deliveryRepository = deliveryRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public void createSupplyOrder(List<ProductAbstractItem> productsList) {
        final LocalDateTime TS = LocalDateTime.now();

        SupplyOrder supplyOrder = new SupplyOrder(productsList, TS, TS.toString());
        supplyOrderRepository.save(supplyOrder);
    }

    @Override
    public void sendSupplyOrder(String supplyOrderId) {
        final LocalDateTime TS = LocalDateTime.now();
        final SupplyOrder supplyOrder = supplyOrderRepository.findById(supplyOrderId).orElseThrow();

        supplyOrder.changeSupplyOrderStatus();

        final List<ProductAbstractItem> products = supplyOrder.getSupplyOrderItems();
        final List<RealProductItem> realProductItems = new ArrayList<>();

        for (ProductAbstractItem product : products) {
            final RealProductItem realProduct = productsRepository.findById(product.getName()).orElseThrow();
            realProductItems.add(realProduct);
        }

        Delivery delivery = new Delivery(TS + supplyOrderId, supplyOrderId, realProductItems);
        deliveryRepository.save(delivery);
    }

    @Override
    public void confirmOrder(String supplyOrderId) {
        final SupplyOrder supplyOrder = supplyOrderRepository.findById(supplyOrderId).orElseThrow();
        supplyOrder.changeSupplyOrderStatus();
    }

    @Override
    public SupplyOrderStatus getOrderStatus(String supplyOrderId) {
        final SupplyOrder supplyOrder = supplyOrderRepository.findById(supplyOrderId).orElseThrow();

        return supplyOrder.getSupplyOrderStatus();
    }

    @Override
    public void getSuppliesWithQualityCheck(String deliveryId) {
        final Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();
        final SupplyOrder supplyOrder = supplyOrderRepository.findById(delivery.getSupplyOrderId()).orElseThrow();

        supplyOrder.changeSupplyOrderStatus();

        final boolean isGoodProducts = delivery.checkProductsQuality();

        System.out.println("Продукты в доставке проверяются, статус - " + isGoodProducts);

        // Иначе ничего не делаем, наш заказ в статусе Delivered
        if (!isGoodProducts) {
            this.processSupplyOrderRejection(delivery);
        }
    }

    // Вызывается только внутри.
    // К примеру, когда есть проблема с качеством.
    @Override
    public void processSupplyOrderRejection(Delivery delivery) {
        final SupplyOrder supplyOrder = supplyOrderRepository.findById(delivery.getSupplyOrderId()).orElseThrow();

        System.out.println("Внимание, обнаружен просроченный продукт. Доставка отменена.");

        // Он уже Delivered, поэтому меняем на последнее - Canceled.
        supplyOrder.changeSupplyOrderStatus();
    }

    @Override
    public String getAllSupplyOrders() {
        return supplyOrderRepository.findAll().stream()
            .map(SupplyOrder::toString)
            .collect(Collectors.joining("\n"));
    }

    @Override
    public String getAllDeliveries() {
        return deliveryRepository.findAll().stream()
        .map(Delivery::toString)
        .collect(Collectors.joining("\n"));
    }

    @Override
    public String getAllProducts() {
        return productsRepository.findAll().stream()
        .map(RealProductItem::toString)
        .collect(Collectors.joining("\n"));
    }
}
