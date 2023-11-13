package christmas.exception;

import christmas.exception.dateException.DateException;
import christmas.exception.orderException.EachOrderException;
import christmas.exception.orderException.TotalOrderException;

public class EventException {

    private final EachOrderException eachOrderException = new EachOrderException();
    private final TotalOrderException totalOrderException = new TotalOrderException();
    private final DateException dateException = new DateException();
    private static final String DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public boolean dateCheckMain(String visitDate) {
        try {
            dateException.numberCheck(visitDate);
            dateException.VisitDateRangeCheckMain(visitDate);
        } catch (IllegalArgumentException e) {
            System.out.println(DATE_ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean ordersCheckMain(String[] orders) {
        try {
            eachOrderException.eachOrderCheck(orders);
            totalOrderException.totalOrderCheck(orders);
        } catch (IllegalArgumentException e) {
            System.out.println(INVALID_ORDER_ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}

