package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import christmas.exception.EventException;

public class InputView {
    private final EventException eventException = new EventException();
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PROMPT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!) ";
    private static final String ORDER_INSTRUCTION = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public int userInputVisitDate() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PROMPT_MESSAGE);

        String visitDate;
        do {
            visitDate = Console.readLine().trim();
        } while (!eventException.dateCheckMain(visitDate));

        return Integer.parseInt(visitDate);
    }

    public Order userInputOrders() {
        String[] userOrders;
        do {
            System.out.println(ORDER_INSTRUCTION);
            userOrders = Console.readLine().split(",");
        } while (false);
        return new Order(userOrders);
    }
}
