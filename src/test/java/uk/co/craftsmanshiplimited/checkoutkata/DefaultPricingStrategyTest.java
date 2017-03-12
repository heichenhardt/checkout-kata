package uk.co.craftsmanshiplimited.checkoutkata;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Henrik on 12/03/2017.
 */
public class DefaultPricingStrategyTest {

    private PricingStrategy pricingStrategy;

    @Before
    public void setup() throws Exception {
        this.pricingStrategy = new DefaultPricingStrategy();
    }

    @Test
    public void shouldScanFirstItem() throws Exception {
        assertEquals(50, pricingStrategy.getTotalPrice(Arrays.asList("A")));
    }

    @Test
    public void shouldScanTwoItems() throws Exception {
        assertEquals(80, pricingStrategy.getTotalPrice(Arrays.asList("A", "B")));
    }

    @Test
    public void shouldAllItemInInventory() throws Exception {
        assertEquals(115, pricingStrategy.getTotalPrice(Arrays.asList("A", "B", "C", "D")));
    }

    @Test
    public void shouldCalculatePriceForMultiBuysA() throws Exception {
        assertEquals(50, pricingStrategy.getTotalPrice(Arrays.asList("A")));
        assertEquals(100, pricingStrategy.getTotalPrice(Arrays.asList("A", "A")));
        assertEquals(130, pricingStrategy.getTotalPrice(Arrays.asList("A", "A", "A")));
        assertEquals(180, pricingStrategy.getTotalPrice(Arrays.asList("A", "A", "A", "A")));
        assertEquals(230, pricingStrategy.getTotalPrice(Arrays.asList("A", "A", "A", "A", "A")));
        assertEquals(260, pricingStrategy.getTotalPrice(Arrays.asList("A", "A", "A", "A", "A","A")));
    }

    @Test
    public void shouldCalculatePriceForMultiBuysB() throws Exception {
        assertEquals(30, pricingStrategy.getTotalPrice(Arrays.asList("B")));
        assertEquals(45, pricingStrategy.getTotalPrice(Arrays.asList("B", "B")));
        assertEquals(75, pricingStrategy.getTotalPrice(Arrays.asList("B", "B", "B")));
        assertEquals(90, pricingStrategy.getTotalPrice(Arrays.asList("B", "B", "B", "B")));
        assertEquals(120, pricingStrategy.getTotalPrice(Arrays.asList("B", "B", "B", "B", "B")));
    }

    @Test
    public void shouldCalculatePriceForMultiBuysC() throws Exception {
        assertEquals(20, pricingStrategy.getTotalPrice(Arrays.asList("C")));
        assertEquals(40, pricingStrategy.getTotalPrice(Arrays.asList("C", "C")));
        assertEquals(60, pricingStrategy.getTotalPrice(Arrays.asList("C", "C", "C")));
        assertEquals(80, pricingStrategy.getTotalPrice(Arrays.asList("C", "C", "C", "C")));
    }

    @Test
    public void shouldCalculatePriceForMultiBuysD() throws Exception {
        assertEquals(15, pricingStrategy.getTotalPrice(Arrays.asList("D")));
        assertEquals(30, pricingStrategy.getTotalPrice(Arrays.asList("D", "D")));
        assertEquals(45, pricingStrategy.getTotalPrice(Arrays.asList("D", "D", "D")));
        assertEquals(60, pricingStrategy.getTotalPrice(Arrays.asList("D", "D", "D", "D")));
    }

    @Test
    public void shouldCalculatePriceForBasket() throws Exception {
        assertEquals(215, pricingStrategy.getTotalPrice(Arrays.asList("A", "B", "C", "D","A", "B", "C", "D")));
    }

    @Test
    public void shouldCalculatePriceForEmpty() throws Exception {
        assertEquals(0, pricingStrategy.getTotalPrice(new ArrayList<>()));
    }

}
