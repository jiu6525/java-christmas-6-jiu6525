package christmas.exception;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventExceptionTest extends NsTest {
    @Test
    @DisplayName("비정상적인 날짜 입력시 예외 테스트")
    void VisitDateRangeCheckTest() {
        assertSimpleTest(() -> {
            runException("32");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("주문개수가 20개 이하인 경우 예외 테스트 (21개 입력)")
    void orderMenuQuantityCheckTest() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-10,바비큐립-5,초코케이크-5,제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("중복메뉴 예외 테스트")
    void orderMenuDuplicateCheckTest() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-1,티본스테이크-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}