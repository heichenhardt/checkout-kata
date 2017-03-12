package uk.co.craftsmanshiplimited.checkoutkata;

import static uk.co.craftsmanshiplimited.checkoutkata.Till.ScannerResponse.ERROR;
import static uk.co.craftsmanshiplimited.checkoutkata.Till.ScannerResponse.OK;

/**
 * Created by Henrik on 12/03/2017.
 */
public class Till {

    enum ScannerResponse {
        OK, ERROR
    }

    private int sum;

    public ScannerResponse scan(final String stockKeepkingUnit) {
        if(stockKeepkingUnit.equals("A")) {
            sum += 50;
            return OK;
        } else if (stockKeepkingUnit.equals("B")){
            sum += 30;
            return OK;
        } else {
            return  ERROR;
        }

    }

    public int getSum() {
        return sum;
    }
}
