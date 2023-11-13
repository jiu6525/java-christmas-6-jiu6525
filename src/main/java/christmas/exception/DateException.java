package christmas.exception;

public class DateException {
    public void numberCheck(String visitDate) {
        try {
            Integer.parseInt(visitDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}
