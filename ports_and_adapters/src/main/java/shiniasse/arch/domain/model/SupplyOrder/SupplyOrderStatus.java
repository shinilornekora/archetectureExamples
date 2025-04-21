package shiniasse.arch.domain.model.SupplyOrder;


public enum SupplyOrderStatus {
    CREATED,
    CONFIRMED,

    // Тут имеется в виду - в пути :) 
    PROCESSING,
    DELIVERED,

    // Доставлена, однако была реджектнута проверяющим ресторана
    CANCELED
}
