package christmas.exception.orderException;

import static christmas.config.DiscountConfig.MAXIMUM_ORDER_QUANTITY;

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

    private void orderMenuQuantityCheckMain(String[] orders) {
        if (orderMenuQuantityCheckSub(orders) > MAXIMUM_ORDER_QUANTITY) {
            throw new IllegalArgumentException();
        }
    }

    private int orderMenuQuantityCheckSub(String[] orders) {
        return new Order(orders).calculateOrderQuantity();
    }

}
