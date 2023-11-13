package christmas.domain;

public record Amount(int mainEventDiscountAmount, int dateDiscountAmount, int specialDateDiscountAmount,
                     int totalOrderAmount) {
}
