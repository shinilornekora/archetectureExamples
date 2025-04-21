package shiniasse.arch.domain.model.Delivery;

import java.time.LocalDateTime;

public class RealProductItem {
    private final String id;
    private final String name;
    private final double price;
    private final LocalDateTime expirationDateTime;

    public RealProductItem(String id, String name, double price, LocalDateTime expirationDateTime) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return name + " (" + price + " руб.)";
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }
}