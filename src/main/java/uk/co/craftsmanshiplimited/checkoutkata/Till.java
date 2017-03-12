package uk.co.craftsmanshiplimited.checkoutkata;

import java.util.*;
import java.util.stream.Collectors;

import static uk.co.craftsmanshiplimited.checkoutkata.Till.ScannerResponse.ERROR;
import static uk.co.craftsmanshiplimited.checkoutkata.Till.ScannerResponse.OK;

/**
 * Created by Henrik on 12/03/2017.
 */
public class Till {

    private List<String> items;
    private Set<String> availableInInventory;

    enum ScannerResponse {
        OK, ERROR
    }

    public Till() {
        this.items = new LinkedList<String>();
        this.availableInInventory = new HashSet<String>(Arrays.asList("A", "B", "C", "D"));
    }

    private int sum;

    public ScannerResponse scan(final String stockKeepkingUnit) {
        if(availableInInventory.contains(stockKeepkingUnit)) {
            this.items.add(stockKeepkingUnit);
            return OK;
        }
        return ERROR;

    }

    public int getSum() {
        final Map<String, Long> productToCount =
                items.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        return productToCount.entrySet().stream().mapToInt(entry -> calculatePrice(entry)).sum();
    }

    private int calculatePrice(final Map.Entry<String, Long> entry) {
        final String stockKeepkingUnit = entry.getKey();
        int count = entry.getValue().intValue();
        if(stockKeepkingUnit.equals("A")) {
            int multiBuy = count / 3;
            int nonMultiBuy = count % 3;
            return multiBuy * 130 + nonMultiBuy * 50;
        } else if (stockKeepkingUnit.equals("B")){
            return 30;
        } else if (stockKeepkingUnit.equals("C")){
            return 20;
        } else if (stockKeepkingUnit.equals("D")){
            return 15;
        } else {
            throw new IllegalArgumentException("Unknown product in basket");
        }
    }
}
