package christmas.exception.dateException;

import christmas.config.DateConfig;

public class DateException {
    public void numberCheck(String visitDate) {
        try {
            Integer.parseInt(visitDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    public void VisitDateRangeCheckMain(String visitDate) {
        int date = Integer.parseInt(visitDate);
        if (date < DateConfig.MONTH_START || date > DateConfig.MONTH_END) {
            throw new IllegalArgumentException();
        }
    }
}
