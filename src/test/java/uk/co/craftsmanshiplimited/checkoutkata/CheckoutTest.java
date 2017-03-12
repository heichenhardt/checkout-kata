package uk.co.craftsmanshiplimited.checkoutkata;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.ERROR;
import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.OK;

/**
 * Created by Henrik on 12/03/2017.
 */
public class CheckoutTest {

    private Map<String, PricingRule> pricingStrategy;

    private Checkout checkout;

    @Before
    public void setup() throws Exception {
        this.pricingStrategy = new HashMap<>();
        this.pricingStrategy.put("A", new PricingRule("A", 50, 130, 3));
        this.pricingStrategy.put("B", new PricingRule("B", 30, 45, 2));
        this.pricingStrategy.put("C", new PricingRule("C", 20, 20, 1));
        this.pricingStrategy.put("D", new PricingRule("D", 15, 15, 1));

        this.checkout = new Checkout(pricingStrategy);
    }

    @Test
    public void shouldScanFirstItem() throws Exception {
        this.checkout.scan("A");
        assertEquals(50, checkout.getTotalPrice());
    }

    @Test
    public void shouldScanTwoItems() throws Exception {
        this.checkout.scan("A");
        this.checkout.scan("B");
        assertEquals(80, checkout.getTotalPrice());
    }

    @Test
    public void shouldErrorOnScanningItemNotInInventory() throws Exception {
        assertEquals(ERROR, checkout.scan("Z"));
        assertEquals(OK, checkout.scan("A"));
        assertEquals(50, checkout.getTotalPrice());
    }

    @Test
    public void shouldAllItemInInventory() throws Exception {
        assertEquals(OK, checkout.scan("A"));
        assertEquals(OK, checkout.scan("B"));
        assertEquals(OK, checkout.scan("C"));
        assertEquals(OK, checkout.scan("D"));
        assertEquals(115, checkout.getTotalPrice());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysA() throws Exception {
        checkout.scan("A");
        assertEquals(50, checkout.getTotalPrice());
        checkout.scan("A");
        assertEquals(100, checkout.getTotalPrice());
        checkout.scan("A");
        assertEquals(130, checkout.getTotalPrice());
        checkout.scan("A");
        assertEquals(180, checkout.getTotalPrice());
        checkout.scan("A");
        assertEquals(230, checkout.getTotalPrice());
        checkout.scan("A");
        assertEquals(260, checkout.getTotalPrice());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysB() throws Exception {
        checkout.scan("B");
        assertEquals(30, checkout.getTotalPrice());
        checkout.scan("B");
        assertEquals(45, checkout.getTotalPrice());
        checkout.scan("B");
        assertEquals(75, checkout.getTotalPrice());
        checkout.scan("B");
        assertEquals(90, checkout.getTotalPrice());
        checkout.scan("B");
        assertEquals(120, checkout.getTotalPrice());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysC() throws Exception {
        checkout.scan("C");
        assertEquals(20, checkout.getTotalPrice());
        checkout.scan("C");
        assertEquals(40, checkout.getTotalPrice());
        checkout.scan("C");
        assertEquals(60, checkout.getTotalPrice());
        checkout.scan("C");
        assertEquals(80, checkout.getTotalPrice());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysD() throws Exception {
        checkout.scan("D");
        assertEquals(15, checkout.getTotalPrice());
        checkout.scan("D");
        assertEquals(30, checkout.getTotalPrice());
        checkout.scan("D");
        assertEquals(45, checkout.getTotalPrice());
        checkout.scan("D");
        assertEquals(60, checkout.getTotalPrice());
    }

    @Test
    public void shouldCalculatePriceForBasket() throws Exception {
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("C");
        checkout.scan("D");
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("C");
        checkout.scan("D");
        assertEquals(215, checkout.getTotalPrice());
    }

    @Test
    public void shouldCalculatePriceForEmpty() throws Exception {
        assertEquals(0, checkout.getTotalPrice());
    }

}
