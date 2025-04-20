
package shiniasse.arch.domain;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Inventory {
    private final String name;
    private final Map<String, Product> products;

    public Inventory(String name, Map<String, Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, Product> getProducts() {
        return this.products;
    }

    public void addProducts(Map<String, Product> productsToAdd) {
        for (Map.Entry<String, Product> entry : productsToAdd.entrySet()) {
            String productName = entry.getKey();
            Product productToAdd = entry.getValue();

            if (products.containsKey(productName)) {
                Product existingProduct = products.get(productName);
                existingProduct.incrementAmount(existingProduct.getAmount() + productToAdd.getAmount());
            } else {
                products.put(productName, productToAdd);
            }
        }
    }

    // Пытаемся использовать продукты для чего-то.
    public void removeProducts(Map<String, Product> productsToRemove) {
        Set<String> keysToRemove = productsToRemove.keySet();
        for (String productName : keysToRemove) {
            if (products.containsKey(productName)) {
                Product existingProduct = products.get(productName);
                Product productToRemove = productsToRemove.get(productName);

                int newAmount = existingProduct.getAmount() - productToRemove.getAmount();
                if (newAmount > 0) {
                    existingProduct.decrementAmount(productToRemove.getAmount());
                } else {
                    existingProduct.autoCorrect();
                }
            }
        }
    }

    // Убирает всю партию просроченных продуктов.
    public void removeOutdatedProducts() {
        LocalDateTime currentDate = LocalDateTime.now();

        this.products.entrySet().removeIf(entry -> {
            Product product = entry.getValue();
            return product.getExpirationDate().isBefore(currentDate);
        });
    }

    public String printDocumentation() {
        return this.products.values().stream()
                .map(Product::toString)
                .collect(Collectors.joining("\n"));
    }

    public String getCritThresholdProducts() {
        return this.products.values().stream()
            .filter(product -> product.getCriticalThreshold() >= product.getAmount())
            .map(Product::toString)
            .collect(Collectors.joining("\n"));
    }

    public String makeProductsInventorizationWithAutoCorrection() {        
        final String initialState = printDocumentation();

        for (Product productToResearch : this.products.values()) {
            if (productToResearch.getCriticalThreshold() >= productToResearch.getAmount()) {
                productToResearch.autoCorrect();
            }
        }

        final String afterCorrectedState = printDocumentation();

        return (
            """
             - - - - - BEFORE_CORRECTION - - - - - 
            """ +
            initialState +
            """
             - - - - - AFTER_CORRECTION - - - - - 
            """ +
            afterCorrectedState
        );
    } 
}
