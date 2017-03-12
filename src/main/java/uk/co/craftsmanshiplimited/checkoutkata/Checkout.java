package uk.co.craftsmanshiplimited.checkoutkata;

import java.util.*;

import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.ERROR;
import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.OK;

/**
 * Created by Henrik on 12/03/2017.
 */
public class Checkout {

    private final PricingStrategy pricingStrategy;
    private final List<String> items;

    enum ScannerResponse {
        OK, ERROR
    }

    public Checkout(final PricingStrategy pricingStrategy) {
        this.items = new LinkedList<String>();
        this.pricingStrategy = pricingStrategy;
    }

    public ScannerResponse scan(final String stockKeepkingUnit) {
        if(pricingStrategy.contains(stockKeepkingUnit)) {
            this.items.add(stockKeepkingUnit);
            return OK;
        }
        return ERROR;
    }

    public int getTotalPrice() {
        return this.pricingStrategy.getTotalPrice(items);
    }
}
