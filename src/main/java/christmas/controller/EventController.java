package christmas.controller;

import christmas.view.InputView;

public class EventController {

    public void userInfo() {
        InputView inputView = new InputView();
        int visitDate = inputView.userInputVisitDate();

    }
}
