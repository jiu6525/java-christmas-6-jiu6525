package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountCalculatorTest {

    DiscountCalculator discountCalculator = new DiscountCalculator();
    Order order = new Order(new String[]{"티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"});
    AmountCalculator amountCalculator = new AmountCalculator(discountCalculator.calculateDiscountRate(order, 3));

    @Test
    @DisplayName("할인금액을 통한 예상 결제금액 계산 테스트")
    void paymentTest() {
        int totalOrderAmount = amountCalculator.amount().totalOrderAmount();
        int date = amountCalculator.amount().dateDiscountAmount();
        int mainEvent = amountCalculator.amount().mainEventDiscountAmount();
        int specialDate = amountCalculator.amount().specialDateDiscountAmount();
        int payment = amountCalculator.getPayment();
        assertThat(totalOrderAmount - date - mainEvent - specialDate).isEqualTo(payment);
    }
}