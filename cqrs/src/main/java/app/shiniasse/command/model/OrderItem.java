package app.shiniasse.command.model;

public class OrderItem {
    private final String dishId;
    private final String name;
    private int quantity;
    private boolean prepared;

    public OrderItem(String dishId, String name, int qty) {
        this.dishId = dishId;
        this.name = name;
        this.quantity = qty;
        this.prepared = false;
    }

    public String getDishId() { return dishId; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public boolean isPrepared() { return prepared; }

    public void increaseQuantity(int delta) {
        this.quantity += delta;
    }

    public void markPrepared() {
        this.prepared = true;
    }
}
