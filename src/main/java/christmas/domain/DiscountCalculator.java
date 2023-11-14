package christmas.domain;

import static christmas.config.DiscountConfig.DEFAULT_AMOUNT;
import static christmas.config.DiscountConfig.MINIMUM_PURCHASE_AMOUNT_FOR_DISCOUNT_EVENT;

import christmas.config.DateConfig;
import christmas.config.DiscountConfig;
import christmas.domain.enums.Menu;
import christmas.domain.enums.Menu.MenuType;
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
        return minimumDiscountAmountCheck(visitDate, dateDiscountAmount, totalOrderAmount);
    }

    private Amount minimumDiscountAmountCheck(int visitDate, int dateDiscountAmount, int totalOrderAmount) {
        if (totalOrderAmount <= MINIMUM_PURCHASE_AMOUNT_FOR_DISCOUNT_EVENT) {
            return new Amount(DEFAULT_AMOUNT, DEFAULT_AMOUNT, DEFAULT_AMOUNT, totalOrderAmount);
        }
        return new Amount(mainEventDiscountCalculate(visitDate),
                dateDiscountAmount,
                specialDateDiscountCalculate(visitDate),
                totalOrderAmount);
    }

    public int mainEventDiscountCalculate(int visitDate) {
        if (visitDate <= DateConfig.MAIN_EVENT_DATE) {
            return DiscountConfig.DEFAULT_DISCOUNT_AMOUNT
                    + (visitDate - 1) * DiscountConfig.MAIN_EVENT_DISCOUNT_RATE;
        }
        return DEFAULT_AMOUNT;
    }

    public int specialDateDiscountCalculate(int visitDate) {
        if (visitDate % DateConfig.WEEK == DateConfig.SPECIAL_DISCOUNT_DATE
                || visitDate == DateConfig.MAIN_EVENT_DATE) {
            return DiscountConfig.DEFAULT_DISCOUNT_AMOUNT;
        }
        return DEFAULT_AMOUNT;
    }

    public int dateDiscountCalculate(int visitDate, MenuType menuType, Integer quantity) {
        if (isWeekly(visitDate) && menuType == MenuType.MAIN) {
            return DiscountConfig.WEEKLY_DISCOUNT_AMOUNT * quantity;
        }
        if (!isWeekly(visitDate) && menuType == MenuType.DESSERT) {
            return DiscountConfig.WEEKLY_DISCOUNT_AMOUNT * quantity;
        }
        return DEFAULT_AMOUNT;
    }

    public boolean isWeekly(int day) {
        return day % DateConfig.WEEK == DateConfig.WEEKLY_DISCOUNT_START_DATE
                || day % DateConfig.WEEK == DateConfig.WEEKLY_DISCOUNT_END_DATE;
    }

}
