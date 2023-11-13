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
            dateDiscountAmount += dateDiscountCalculate(visitDate, menu.getType(), entry.getValue());
        }
        return new Amount(mainEventDiscountCalculate(visitDate),
                dateDiscountAmount,
                specialDateDiscountCalculate(visitDate),
                totalOrderAmount);
    }

    private int specialDateDiscountCalculate(int visitDate) {
        return 0;
    }

    private int mainEventDiscountCalculate(int visitDate) {
        return 0;
    }

    private int dateDiscountCalculate(int visitDate, MenuType type, Integer value) {
        return 0;
    }
}
