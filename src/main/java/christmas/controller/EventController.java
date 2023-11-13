package christmas.controller;

import christmas.domain.Amount;
import christmas.domain.AmountCalculator;
import christmas.domain.DiscountCalculator;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    public void userInfo() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        DiscountCalculator discountCalculator = new DiscountCalculator();

        int visitDate = inputView.userInputVisitDate();
        Order userOrders = inputView.userInputOrders();

        Amount amount = discountCalculator.calculateDiscountRate(userOrders, visitDate);
        AmountCalculator amountCalculator = new AmountCalculator(amount);

        outputView.orderResultPrint(visitDate, userOrders, amountCalculator);
    }
}
