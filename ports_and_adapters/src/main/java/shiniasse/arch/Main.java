package shiniasse.arch;

import shiniasse.arch.adapter.primary.console.ConsoleOrderManagementUI;
import shiniasse.arch.adapter.secondary.InMemoryDeliveryRepository;
import shiniasse.arch.adapter.secondary.InMemoryProductsRepository;
import shiniasse.arch.adapter.secondary.InMemorySupplyOrderRepository;
import shiniasse.arch.domain.port.primary.OrderProcessingSystem;
import shiniasse.arch.domain.port.secondary.DeliveryRepository;
import shiniasse.arch.domain.port.secondary.ProductsRepository;
import shiniasse.arch.domain.port.secondary.SupplyOrderRepository;
import shiniasse.arch.domain.service.OrderProcessingSystemService;

public class Main {
    public static void main(String[] args) {
        // ?????????? ????????? ????????
        DeliveryRepository deliveryRepository = new InMemoryDeliveryRepository();
        ProductsRepository productsRepository = new InMemoryProductsRepository();
        SupplyOrderRepository supplyOrderRepository = new InMemorySupplyOrderRepository();

        // ?????? ???????? ????????
        productsRepository.populateWithTestData();

        // ?????????????? ????????
        OrderProcessingSystem orderProcessingSystem = new OrderProcessingSystemService(
            supplyOrderRepository, 
            deliveryRepository, 
            productsRepository
        );
        
        // ?????????? ???????? ???????
        ConsoleOrderManagementUI ui = new ConsoleOrderManagementUI(orderProcessingSystem);

        // ?? ? ????????? CLI
        ui.start();
    }
}