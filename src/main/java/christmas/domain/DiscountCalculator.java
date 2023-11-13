package christmas.domain;

import christmas.enums.Menu;
import christmas.enums.Menu.MenuType;
import java.util.Map.Entry;

public class DiscountCalculator {

    public Amount calculateDiscountRate(Order userOrders, int visitDate) {
        int dateDiscountAmount = 0;
        int totalOrderAmount = 0;
        for (Entry<String, Integer> entry : userOrders.getUserOrders().entrySet()) {
            Menu menu = Menu.valueOf(entry.getKey());
            totalOrderAmount += menu.getPrice() * entry.getValue();
            dateDiscountAmount += calculateDiscountByDate(visitDate, menu.getType(), entry.getValue());
        }
        return new Amount(calculateMainEventDiscount(visitDate),
                dateDiscountAmount,
                calculateSpecialDateDiscount(visitDate),
                totalOrderAmount);
    }

    private int calculateSpecialDateDiscount(int visitDate) {
        return 0;
    }

    private int calculateMainEventDiscount(int visitDate) {
        return 0;
    }

    private int calculateDiscountByDate(int visitDate, MenuType type, Integer value) {
        return 0;
    }
}
