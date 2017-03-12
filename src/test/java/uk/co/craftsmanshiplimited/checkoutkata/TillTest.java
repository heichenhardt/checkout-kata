package uk.co.craftsmanshiplimited.checkoutkata;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.co.craftsmanshiplimited.checkoutkata.Till.ScannerResponse.ERROR;
import static uk.co.craftsmanshiplimited.checkoutkata.Till.ScannerResponse.OK;

/**
 * Created by Henrik on 12/03/2017.
 */
public class TillTest {

    private Till till;

    @Before
    public void setup() throws Exception {
        this.till = new Till();
    }

    @Test
    public void shouldScanFirstItem() throws Exception {
        this.till.scan("A");
        assertEquals(50, till.getSum());
    }

    @Test
    public void shouldScanTwoItems() throws Exception {
        this.till.scan("A");
        this.till.scan("B");
        assertEquals(80, till.getSum());
    }

    @Test
    public void shouldErrorOnScanningItemNotInInventory() throws Exception {
        assertEquals(ERROR, till.scan("Z"));
        assertEquals(OK, till.scan("A"));
        assertEquals(50, till.getSum());
    }

    @Test
    public void shouldAllItemInInventory() throws Exception {
        assertEquals(OK, till.scan("A"));
        assertEquals(OK, till.scan("B"));
        assertEquals(OK, till.scan("C"));
        assertEquals(OK, till.scan("D"));
        assertEquals(115, till.getSum());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysA() throws Exception {
        till.scan("A");
        assertEquals(50, till.getSum());
        till.scan("A");
        assertEquals(100, till.getSum());
        till.scan("A");
        assertEquals(130, till.getSum());
        till.scan("A");
        assertEquals(180, till.getSum());
        till.scan("A");
        assertEquals(230, till.getSum());
        till.scan("A");
        assertEquals(260, till.getSum());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysB() throws Exception {
        till.scan("B");
        assertEquals(30, till.getSum());
        till.scan("B");
        assertEquals(45, till.getSum());
        till.scan("B");
        assertEquals(75, till.getSum());
        till.scan("B");
        assertEquals(90, till.getSum());
        till.scan("B");
        assertEquals(120, till.getSum());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysC() throws Exception {
        till.scan("C");
        assertEquals(20, till.getSum());
        till.scan("C");
        assertEquals(40, till.getSum());
        till.scan("C");
        assertEquals(60, till.getSum());
        till.scan("C");
        assertEquals(80, till.getSum());
    }

    @Test
    public void shouldCalculatePriceForMultiBuysD() throws Exception {
        till.scan("D");
        assertEquals(15, till.getSum());
        till.scan("D");
        assertEquals(30, till.getSum());
        till.scan("D");
        assertEquals(45, till.getSum());
        till.scan("D");
        assertEquals(60, till.getSum());
    }

}
