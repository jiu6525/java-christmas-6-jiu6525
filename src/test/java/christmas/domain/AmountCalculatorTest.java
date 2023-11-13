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

    @Test
    @DisplayName("할인전 주문금액이 120000원 이상일때 샴페인 증정 테스트")
    void giftEventTest() {
        assertThat(amountCalculator.amount().totalOrderAmount()).isGreaterThanOrEqualTo(120000);
        assertThat(amountCalculator.giftEvent()).isEqualTo("샴페인 1개");
    }

    @Test
    @DisplayName("할인금액이 20000원 이상일때 산타뱃지 증정 테스트")
    void firstEventBadgeTest() {
        assertThat(amountCalculator.getTotalDiscountAmount()).isGreaterThanOrEqualTo(20000);
        assertThat(amountCalculator.badgeEvent()).isEqualTo("산타");
    }

    @Test
    @DisplayName("할인금액이 10000원 이상일때 트리뱃지 증정 테스트")
    void secondEventBadgeTest() {
        Order order = new Order(new String[]{"티본스테이크-1", "초코케이크-4"});
        AmountCalculator amountCalculator = new AmountCalculator(discountCalculator.calculateDiscountRate(order, 3));
        System.out.println(amountCalculator.getTotalDiscountAmount());
        assertThat(amountCalculator.getTotalDiscountAmount()).isGreaterThanOrEqualTo(10000);
        assertThat(amountCalculator.badgeEvent()).isEqualTo("트리");
    }
}