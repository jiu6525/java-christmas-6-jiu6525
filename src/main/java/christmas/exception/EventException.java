package christmas.exception;

public class EventException {
    private final DateException dateException = new DateException();
    private static final String DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

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
}
