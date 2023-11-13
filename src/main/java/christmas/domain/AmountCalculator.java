package christmas.domain;

import christmas.enums.Prize;

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
}
