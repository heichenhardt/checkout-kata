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
    private final List<String> items;

    enum ScannerResponse {
        OK, ERROR
    }

    public Checkout(final Map<String, PricingRule> pricingStrategy) {
        this.items = new LinkedList<String>();
        this.pricingStrategy = pricingStrategy;
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
        final String stockKeepingUnit = entry.getKey();
        int count = entry.getValue().intValue();
        if(pricingStrategy.get(stockKeepingUnit) != null) {
            return pricingStrategy.get(stockKeepingUnit).getPrice(count);
        } else {
            throw new IllegalArgumentException("Unknown product in basket");
        }
    }
}
