package shiniasse.arch.domain.model.Delivery;

import java.time.LocalDateTime;

public class RealProductItem {
    private final String id;
    private final String name;
    private final double price;
    private final double quantity;
    private final LocalDateTime expirationDateTime;

    public RealProductItem(String id, String name, double price, double quantity, LocalDateTime expirationDateTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDateTime = expirationDateTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    @Override
    public String toString() {
        return (
            "ID продукта - " + this.getId() + "\n" +
            "Имя продукта - " + this.getName() + "\n" +
            "Цена продукта" + this.getPrice() + "\n" +
            "Количество продукта - " + this.getQuantity() + "\n" +
            "Срок годности (до) - " + this.getExpirationDateTime().toString() + "\n"
        );
    }
}