package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountCalculatorTest {

    DiscountCalculator discountCalculator = new DiscountCalculator();

    @Test
    @DisplayName("23.12.01(금) 요일 주말 확인 테스트")
    void isWeekendTest() {
        assertThat(discountCalculator.isWeekly(1)).isTrue();
    }

    @Test
    @DisplayName("23.12.03(일) 요일 평일 확인 테스트")
    void isWeekdayTest() {
        assertThat(discountCalculator.isWeekly(3)).isFalse();
    }

    @Test
    @DisplayName("주말 할인 테스트")
    void WeekendDiscountAmountCheckTest() {
        int visitDate = 1;
        Order order = new Order(new String[]{"티본스테이크-2", "초코케이크-3", "제로콜라-1"});
        AmountCalculator amountCalculator = new AmountCalculator(
                discountCalculator.calculateDiscountRate(order, visitDate));
        assertThat(amountCalculator.amount().dateDiscountAmount()).isEqualTo(4046);
    }

    @Test
    @DisplayName("평일 할인 테스트")
    void WeekdayDiscountAmountCheckTest() {
        int visitDate = 3;
        Order order = new Order(new String[]{"티본스테이크-2", "초코케이크-3", "제로콜라-1"});
        AmountCalculator amountCalculator = new AmountCalculator(
                discountCalculator.calculateDiscountRate(order, visitDate));
        assertThat(amountCalculator.amount().dateDiscountAmount()).isEqualTo(6069);
    }

    @Test
    @DisplayName("메인이벤트(크리스마스 디데이 할인) 최대할인금액 테스트")
    void mainEventDiscountTest() {
        assertThat(discountCalculator.mainEventDiscountCalculate(25)).isEqualTo(3400);
    }

    @Test
    @DisplayName("특별할인(달력에 별) 할인금액 테스트")
    void specialDateDiscountTest() {
        assertThat(discountCalculator.specialDateDiscountCalculate(3)).isEqualTo(1000);
    }

    @Test
    @DisplayName("최소 할인 적용금액 테스트")
    void minimumDiscountAmountCheckTest() {
        int visitDate = 3;
        Order order = new Order(new String[]{"아이스크림-1", "제로콜라-1"});
        AmountCalculator amountCalculator = new AmountCalculator(
                discountCalculator.calculateDiscountRate(order, visitDate));
        assertThat(amountCalculator.getPayment()).isEqualTo(amountCalculator.amount().totalOrderAmount());
    }
}