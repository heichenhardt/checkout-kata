package uk.co.craftsmanshiplimited.checkoutkata;

/**
 * Created by Henrik on 12/03/2017.
 */
public class Till {

    private int sum;

    public void scan(String stockKeepkingUnit) {
        if(stockKeepkingUnit.equals("A")) {
            sum += 50;
        } else if (stockKeepkingUnit.equals("B")){
            sum += 30;
        }
    }

    public int getSum() {
        return sum;
    }
}
