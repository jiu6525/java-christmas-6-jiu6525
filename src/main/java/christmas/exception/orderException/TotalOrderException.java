package christmas.exception.orderException;

import static christmas.config.DiscountConfig.MAXIMUM_ORDER_QUANTITY;

import christmas.domain.Order;
import christmas.enums.Menu;
import christmas.enums.Menu.MenuType;
import java.util.Arrays;

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

    private void orderOnlyDrinkCheckMain(String[] orders) {
        if (orderOnlyDrinkCheckSub(orders)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean orderOnlyDrinkCheckSub(String[] orders) {
        return Arrays.stream(orders)
                .map(order -> order.trim().split("-"))
                .map(menuAndQuantity -> menuAndQuantity[0])
                .allMatch(this::isDrink);
    }

    private boolean isDrink(String userMenu) {
        return Menu.valueOf(userMenu).getType() == MenuType.DRINK;
    }
}
