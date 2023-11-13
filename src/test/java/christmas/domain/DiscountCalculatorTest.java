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
    @DisplayName("메인이벤트(크리스마스 디데이 할인) 최대할인금액 테스트")
    void mainEventDiscountTest() {
        assertThat(discountCalculator.mainEventDiscountCalculate(25)).isEqualTo(3400);
    }

    @Test
    @DisplayName("특별할인(달력에 별) 할인금액 테스트")
    void specialDateDiscountTest() {
        assertThat(discountCalculator.specialDateDiscountCalculate(3)).isEqualTo(1000);
    }
}