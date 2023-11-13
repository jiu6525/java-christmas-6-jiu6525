package christmas.exception.orderException;

import christmas.domain.Order;

public class TotalOrderException {


    private void orderMenuDuplicateCheckMain(String[] orders) {
        if (!orderDuplicateCheckSub(orders)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean orderDuplicateCheckSub(String[] userOrders) {
        return new Order(userOrders).getUserOrders().size() == userOrders.length;
    }
}
