package uk.co.craftsmanshiplimited.checkoutkata;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.ERROR;
import static uk.co.craftsmanshiplimited.checkoutkata.Checkout.ScannerResponse.OK;

/**
 * Created by Henrik on 12/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CheckoutTest {

    @Mock
    private PricingStrategy pricingStrategy;

    @InjectMocks
    private Checkout checkout;

    @Test
    public void shouldErrorOnScanningItemNotInInventory() throws Exception {
        when(pricingStrategy.contains("Z")).thenReturn(false);
        when(pricingStrategy.contains("A")).thenReturn(true);
        assertEquals(ERROR, checkout.scan("Z"));
        assertEquals(OK, checkout.scan("A"));
    }

}
