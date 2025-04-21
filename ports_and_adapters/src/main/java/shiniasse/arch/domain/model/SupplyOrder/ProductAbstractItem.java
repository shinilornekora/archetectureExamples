package shiniasse.arch.domain.model.SupplyOrder;

// Абстрактный продукт не имеет срока годности.
// Пусть ресторану важно чтобы он в этот же день был свежим.
// Проверка свежести продукта определяется в доставке.
public class ProductAbstractItem {
    private final String id;
    private final String name;
    private final double price;
    private final int quantity;

    public ProductAbstractItem(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return (
            "ID продукта - " + this.getId() + "\n" +
            "Имя продукта - " + this.getName() + "\n" +
            "Цена продукта - " + this.getPrice() + "\n" +
            "Количество продукта - " + this.getQuantity() + "\n"
        );
    }
}