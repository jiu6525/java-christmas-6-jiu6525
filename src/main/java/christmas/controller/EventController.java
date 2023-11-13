package christmas.controller;

import christmas.domain.Order;
import christmas.view.InputView;

public class EventController {

    public void userInfo() {
        InputView inputView = new InputView();
        int visitDate = inputView.userInputVisitDate();
        Order userOrders = inputView.userInputOrders();
    }
}
