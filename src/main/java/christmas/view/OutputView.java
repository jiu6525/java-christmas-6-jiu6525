package christmas.view;

import christmas.domain.AmountCalculator;
import christmas.domain.Order;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class OutputView {

    public static final String EVENT_PREVIEW_MESSAGE_FORMAT = "%d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n";
    public static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    public static final String TOTAL_ORDER_AMOUNT_MESSAGE_FORMAT = "\n<할인 전 총주문 금액>\n%s원\n";
    public static final String GIFT_MENU_HEADER = "\n<증정 메뉴>";
    public static final String DISCOUNT_DETAILS_HEADER = "\n<혜택 내역>";
    public static final String TOTAL_DISCOUNT_AMOUNT_MESSAGE_FORMAT = "\n<총혜택 금액>\n%s원\n";
    public static final String PAYMENT_AMOUNT_MESSAGE_FORMAT = "\n<할인 후 예상 결제 금액>\n%s원\n";
    public static final String EVENT_BADGE_MESSAGE_FORMAT = "\n<12월 이벤트 배지>\n%s\n";
    public static final String CHRISTMAS_DISCOUNT_LABEL = "크리스마스 디데이 할인";
    public static final String WEEKDAY_DISCOUNT_LABEL = "평일 할인";
    public static final String SPECIAL_DATE_DISCOUNT_LABEL = "특별 할인";
    public static final String GIFT_EVENT_LABEL = "증정 이벤트";
    public static final String NO_DISCOUNT_MESSAGE = "없음";
    public static final String ORDER_MENU_BODY = "%s %d개\n";


    private final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);

    public void orderResultPrint(int visitDate, Order userOrders, AmountCalculator amountCalculator) {
        printEventPreview(visitDate);
        printOrderMenu(userOrders.getUserOrders());
        printTotalOrderAmount(amountCalculator.amount().totalOrderAmount());
        printGiftMenu(amountCalculator.giftEvent());
        printDiscountDetails(amountCalculator);
        printTotalDiscountAmount(amountCalculator.getTotalDiscountAmount());
        printPaymentAmount(amountCalculator.getPayment());
        printEventBadge(amountCalculator.badgeEvent());
    }

    private void printEventPreview(int visitDate) {
        System.out.printf(EVENT_PREVIEW_MESSAGE_FORMAT, visitDate);
    }

    private void printOrderMenu(Map<String, Integer> userOrders) {
        System.out.println(ORDER_MENU_HEADER);
        userOrders.forEach(
                (menuName, quantity) -> System.out.printf(ORDER_MENU_BODY, menuName, quantity));
    }

    private void printTotalOrderAmount(int totalOrderAmount) {
        System.out.printf(TOTAL_ORDER_AMOUNT_MESSAGE_FORMAT, numberFormat.format(totalOrderAmount));
    }

    private void printGiftMenu(String giftEvent) {
        System.out.println(GIFT_MENU_HEADER);
        System.out.println(giftEvent);
    }

    private void printDiscountDetails(AmountCalculator amountCalculator) {
        System.out.println(DISCOUNT_DETAILS_HEADER);
        displayDiscountDetails(amountCalculator);
    }

    private void printTotalDiscountAmount(int totalDiscountAmount) {
        System.out.printf(TOTAL_DISCOUNT_AMOUNT_MESSAGE_FORMAT,
                numberFormat.format(-totalDiscountAmount));
    }

    private void printPaymentAmount(int paymentAmount) {
        System.out.printf(PAYMENT_AMOUNT_MESSAGE_FORMAT, numberFormat.format(paymentAmount));
    }

    private void printEventBadge(String badgeEvent) {
        System.out.printf(EVENT_BADGE_MESSAGE_FORMAT, badgeEvent);
    }

    private void displayDiscountDetails(AmountCalculator amountCalculator) {
        StringBuilder message = new StringBuilder();
        Map<String, Integer> board = discountAmountBoard(amountCalculator);
        board.forEach((label, amount) -> appendDiscount(message, label, amount));

        if (message.length() == 0) {
            message.append(NO_DISCOUNT_MESSAGE);
        }
        System.out.println(message);
    }

    private static Map<String, Integer> discountAmountBoard(AmountCalculator amountCalculator) {
        return Map.of(
                CHRISTMAS_DISCOUNT_LABEL, amountCalculator.amount().mainEventDiscountAmount(),
                WEEKDAY_DISCOUNT_LABEL, amountCalculator.amount().dateDiscountAmount(),
                SPECIAL_DATE_DISCOUNT_LABEL, amountCalculator.amount().specialDateDiscountAmount(),
                GIFT_EVENT_LABEL, amountCalculator.getGiftDiscountAmount()
        );
    }

    private void appendDiscount(StringBuilder message, String discountName, int discountAmount) {
        if (discountAmount > 0) {
            message.append(discountName).append(": ").append(numberFormat.format(-discountAmount)).append("\n");
        }
    }
}