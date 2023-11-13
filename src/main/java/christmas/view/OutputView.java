package christmas.view;

import christmas.domain.AmountCalculator;
import christmas.domain.Order;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class OutputView {

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
        System.out.printf(OutputConstants.EVENT_PREVIEW_MESSAGE_FORMAT, visitDate);
    }

    private void printOrderMenu(Map<String, Integer> userOrders) {
        System.out.println(OutputConstants.ORDER_MENU_HEADER);
        userOrders.forEach(
                (menuName, quantity) -> System.out.printf(OutputConstants.ORDER_MENU_BODY, menuName, quantity));
    }

    private void printTotalOrderAmount(int totalOrderAmount) {
        System.out.printf(OutputConstants.TOTAL_ORDER_AMOUNT_MESSAGE_FORMAT, numberFormat.format(totalOrderAmount));
    }

    private void printGiftMenu(String giftEvent) {
        System.out.println(OutputConstants.GIFT_MENU_HEADER);
        System.out.println(giftEvent);
    }

    private void printDiscountDetails(AmountCalculator amountCalculator) {
        System.out.println(OutputConstants.DISCOUNT_DETAILS_HEADER);
        displayDiscountDetails(amountCalculator);
    }

    private void printTotalDiscountAmount(int totalDiscountAmount) {
        System.out.printf(OutputConstants.TOTAL_DISCOUNT_AMOUNT_MESSAGE_FORMAT,
                numberFormat.format(-totalDiscountAmount));
    }

    private void printPaymentAmount(int paymentAmount) {
        System.out.printf(OutputConstants.PAYMENT_AMOUNT_MESSAGE_FORMAT, numberFormat.format(paymentAmount));
    }

    private void printEventBadge(String badgeEvent) {
        System.out.printf(OutputConstants.EVENT_BADGE_MESSAGE_FORMAT, badgeEvent);
    }


    private void displayDiscountDetails(AmountCalculator amountCalculator) {
        StringBuilder message = new StringBuilder();
        Map<String, Integer> board = discountAmountBoard(amountCalculator);
        board.forEach((label, amount) -> appendDiscount(message, label, amount));

        if (message.length() == 0) {
            message.append(OutputConstants.NO_DISCOUNT_MESSAGE);
        }
        System.out.print(message);
    }

    private static Map<String, Integer> discountAmountBoard(AmountCalculator amountCalculator) {
        Map<String, Integer> board = new LinkedHashMap<>();
        board.put(OutputConstants.CHRISTMAS_DISCOUNT_LABEL, amountCalculator.amount().mainEventDiscountAmount());
        board.put(OutputConstants.WEEKDAY_DISCOUNT_LABEL, amountCalculator.amount().dateDiscountAmount());
        board.put(OutputConstants.SPECIAL_DATE_DISCOUNT_LABEL, amountCalculator.amount().specialDateDiscountAmount());
        board.put(OutputConstants.GIFT_EVENT_LABEL, amountCalculator.getGiftDiscountAmount());
        return board;
    }

    private void appendDiscount(StringBuilder message, String discountName, int discountAmount) {
        if (discountAmount > 0) {
            message.append(discountName).append(": ").append(numberFormat.format(-discountAmount)).append("\n");
        }
    }
}