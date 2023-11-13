package christmas.domain;

import christmas.enums.EventBadges;
import christmas.enums.Prize;
import java.util.Arrays;

public record AmountCalculator(Amount amount) {

    public int getTotalDiscountAmount() {
        return amount.mainEventDiscountAmount() + amount.dateDiscountAmount() + amount.specialDateDiscountAmount()
                + getGiftDiscountAmount();
    }

    public int getGiftDiscountAmount() {
        if (amount.totalOrderAmount() > Prize.PRODUCT.getMinProductPrice()) {
            return Prize.PRODUCT.getPrice();
        }
        return Prize.NONE.getPrice();
    }

    public int getPayment() {
        return amount.totalOrderAmount() - getTotalDiscountAmount() + getGiftDiscountAmount();
    }

    public String badgeEvent() {
        return Arrays.stream(EventBadges.values())
                .filter(badge -> getTotalDiscountAmount() >= badge.getBadgePrice())
                .findFirst()
                .orElse(EventBadges.NONE)
                .getBadgeMessage();
    }
}
