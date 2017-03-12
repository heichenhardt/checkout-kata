package uk.co.craftsmanshiplimited.checkoutkata;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.ERROR;
import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.OK;

/**
 * Created by Henrik on 12/03/2017.
 */
public class CheckoutTest {

    private Checkout checkout;

    @Before
    public void setup() throws Exception {
        this.checkout = new Checkout();
    }

    @Test
    public void shouldScanFirstItem() throws Exception {
        this.checkout.scan("A");
        assertEquals(50, checkout.getSum());
    }

    @Test
    public void shouldScanTwoItems() throws Exception {
        this.checkout.scan("A");
        this.checkout.scan("B");
        assertEquals(80, checkout.getSum());
    }

    @Test
    public void shouldErrorOnScanningItemNotInInventory() throws Exception {
        assertEquals(ERROR, checkout.scan("Z"));
        assertEquals(OK, checkout.scan("A"));
        assertEquals(50, checkout.getSum());
    }

    @Test
    public void shouldAllItemInInventory() throws Exception {
        assertEquals(OK, checkout.scan("A"));
        assertEquals(OK, checkout.scan("B"));
        assertEquals(OK, checkout.scan("C"));
        assertEquals(OK, checkout.scan("D"));
        assertEquals(115, checkout.getSum());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysA() throws Exception {
        checkout.scan("A");
        assertEquals(50, checkout.getSum());
        checkout.scan("A");
        assertEquals(100, checkout.getSum());
        checkout.scan("A");
        assertEquals(130, checkout.getSum());
        checkout.scan("A");
        assertEquals(180, checkout.getSum());
        checkout.scan("A");
        assertEquals(230, checkout.getSum());
        checkout.scan("A");
        assertEquals(260, checkout.getSum());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysB() throws Exception {
        checkout.scan("B");
        assertEquals(30, checkout.getSum());
        checkout.scan("B");
        assertEquals(45, checkout.getSum());
        checkout.scan("B");
        assertEquals(75, checkout.getSum());
        checkout.scan("B");
        assertEquals(90, checkout.getSum());
        checkout.scan("B");
        assertEquals(120, checkout.getSum());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysC() throws Exception {
        checkout.scan("C");
        assertEquals(20, checkout.getSum());
        checkout.scan("C");
        assertEquals(40, checkout.getSum());
        checkout.scan("C");
        assertEquals(60, checkout.getSum());
        checkout.scan("C");
        assertEquals(80, checkout.getSum());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysD() throws Exception {
        checkout.scan("D");
        assertEquals(15, checkout.getSum());
        checkout.scan("D");
        assertEquals(30, checkout.getSum());
        checkout.scan("D");
        assertEquals(45, checkout.getSum());
        checkout.scan("D");
        assertEquals(60, checkout.getSum());
    }

}
