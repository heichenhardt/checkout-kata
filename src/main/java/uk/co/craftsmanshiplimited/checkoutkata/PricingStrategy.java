package uk.co.craftsmanshiplimited.checkoutkata;

import java.util.List;

/**
 * Created by Henrik on 12/03/2017.
 */
public interface PricingStrategy {

    boolean contains(String item);
    int getTotalPrice(List<String> items);
}
