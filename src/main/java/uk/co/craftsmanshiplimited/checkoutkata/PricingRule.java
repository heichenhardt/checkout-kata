package uk.co.craftsmanshiplimited.checkoutkata;

/**
 * Created by Henrik on 12/03/2017.
 */
public class PricingRule {
    private final String stockKeepingUnit;
    private final int unitPrice;
    private final int multiBuyPrice;
    private final int multiBuyCount;

    public PricingRule(
            final String stockKeepingUnit, final int unitPrice, final int multiBuyPrice, final int multiBuyCount) {
        this.stockKeepingUnit = stockKeepingUnit;
        this.unitPrice = unitPrice;
        this.multiBuyPrice = multiBuyPrice;
        this.multiBuyCount = multiBuyCount;
    }

    public int getPrice(int count) {
        if(count<=0) {
            throw new IllegalArgumentException("Product count must be a positive integer");
        }
        int multiBuy = count / multiBuyCount;
        int nonMultiBuy = count % multiBuyCount;
        return multiBuy * multiBuyPrice + nonMultiBuy * unitPrice;
    }
}
