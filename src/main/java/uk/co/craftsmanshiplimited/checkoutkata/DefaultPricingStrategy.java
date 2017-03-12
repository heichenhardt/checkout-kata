package uk.co.craftsmanshiplimited.checkoutkata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Henrik on 12/03/2017.
 */
public class DefaultPricingStrategy implements PricingStrategy {

    private Map<String, PricingRule> pricingRules;

    public DefaultPricingStrategy() {
        this.pricingRules = new HashMap<>();
        this.pricingRules.put("A", new PricingRule("A", 50, 130, 3));
        this.pricingRules.put("B", new PricingRule("B", 30, 45, 2));
        this.pricingRules.put("C", new PricingRule("C", 20, 20, 1));
        this.pricingRules.put("D", new PricingRule("D", 15, 15, 1));
    }

    @Override
    public boolean contains(String item) {
        return pricingRules.get(item) != null;
    }

    @Override
    public int getTotalPrice(final List<String> items) {
        final Map<String, Long> productToCount =
                items.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        return productToCount.entrySet().stream().mapToInt(this::calculatePrice).sum();
    }

    private int calculatePrice(final Map.Entry<String, Long> entry) {
        final String stockKeepingUnit = entry.getKey();
        int count = entry.getValue().intValue();
        if(pricingRules.get(stockKeepingUnit) != null) {
            return pricingRules.get(stockKeepingUnit).getPrice(count);
        } else {
            throw new IllegalArgumentException("Unknown product in basket");
        }
    }
}
