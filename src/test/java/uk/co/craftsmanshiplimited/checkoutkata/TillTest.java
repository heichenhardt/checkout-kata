package uk.co.craftsmanshiplimited.checkoutkata;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
