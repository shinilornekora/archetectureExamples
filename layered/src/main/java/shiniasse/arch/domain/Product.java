package shiniasse.arch.domain;

import java.time.LocalDateTime;

public class Product {
    private final LocalDateTime expiryDateTime;
    private final TemperatureMode temperatureMode;
    private final int criticalAmount;
    private final int optimalAmount;
    private final String name;
    private int amount;

    final String NEW_LINE = "\n";

    public Product(
        String name, 
        int amount, 
        LocalDateTime expiryDateTime,
        TemperatureMode temperatureMode,
        int criticalAmount,
        int optimalAmount
    ) {
        this.name = name;
        this.amount = amount;
        this.expiryDateTime = expiryDateTime;
        this.temperatureMode = temperatureMode;
        this.criticalAmount = criticalAmount;
        this.optimalAmount = optimalAmount;
    }

    public void decrementAmount(int num) {
        this.amount -= num;
    }

    public void incrementAmount(int num) {
        this.amount += num;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getExpirationDate() {
        return this.expiryDateTime;
    }

    public int getOptimalAmount() {
        return this.optimalAmount;
    }

    public int getCriticalThreshold() {
        return this.criticalAmount;
    }

    public TemperatureMode getTemperatureMode() {
        return this.temperatureMode;
    }

    // Коичество продуктов становится оптимальным
    public void autoCorrect() {
        this.incrementAmount(getOptimalAmount() - getAmount());
    }

    @Override
    public String toString() {
        return (
            this.getName() + " - " + this.getAmount() + " штук(а)" + NEW_LINE +
            this.getExpirationDate().toString() + " - срок годности (до)" + NEW_LINE +
            this.getCriticalThreshold() + " - критическое количество" + NEW_LINE +
            this.getOptimalAmount() + " - нормальное количество" + NEW_LINE +
            this.getTemperatureMode() + " - температурный режим" + NEW_LINE
        );  
    }
}
