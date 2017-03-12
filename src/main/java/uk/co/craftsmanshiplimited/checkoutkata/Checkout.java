package uk.co.craftsmanshiplimited.checkoutkata;

import java.util.*;
import java.util.stream.Collectors;

import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.ERROR;
import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.OK;

/**
 * Created by Henrik on 12/03/2017.
 */
public class Checkout {

    private final Map<String, PricingRule> pricingStrategy;
    private List<String> items;

    enum ScannerResponse {
        OK, ERROR
    }

    public Checkout() {
        this.items = new LinkedList<String>();
        this.pricingStrategy = new HashMap<>();
        this.pricingStrategy.put("A", new PricingRule("A", 50, 130, 3));
        this.pricingStrategy.put("B", new PricingRule("B", 30, 45, 2));
        this.pricingStrategy.put("C", new PricingRule("C", 20, 20, 1));
        this.pricingStrategy.put("D", new PricingRule("D", 15, 15, 1));
    }

    public ScannerResponse scan(final String stockKeepkingUnit) {
        if(pricingStrategy.get(stockKeepkingUnit) != null) {
            this.items.add(stockKeepkingUnit);
            return OK;
        }
        return ERROR;

    }

    public int getTotalPrice() {
        final Map<String, Long> productToCount =
                items.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        return productToCount.entrySet().stream().mapToInt(this::calculatePrice).sum();
    }

    private int calculatePrice(final Map.Entry<String, Long> entry) {
        final String stockKeepkingUnit = entry.getKey();
        int count = entry.getValue().intValue();
        if(pricingStrategy.get(stockKeepkingUnit) != null) {
            return pricingStrategy.get(stockKeepkingUnit).getPrice(count);
        } else {
            throw new IllegalArgumentException("Unknown product in basket");
        }
    }
}
