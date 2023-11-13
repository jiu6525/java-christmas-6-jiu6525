package christmas.exception.orderException;

import christmas.enums.Menu;
import java.util.Arrays;

public class EachOrderException {
    public void eachOrderCheck(String[] orders) {
        Arrays.stream(orders)
                .map(order -> order.trim().split("-"))
                .peek(this::orderFormatCheckMain)
                .map(menuAndQuantity -> menuAndQuantity[0])
                .forEach(this::orderMenuContainsCheckMain);
    }

    private void orderFormatCheckMain(String[] menuAndQuantity) {
        if (!orderFormatCheckSub(menuAndQuantity)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean orderFormatCheckSub(String[] menuAndQuantity) {
        return menuAndQuantity.length == 2 && menuAndQuantity[1].matches("\\d+");
    }

    private void orderMenuContainsCheckMain(String userMenu) {
        if (!orderMenuContainsCheckSub(userMenu)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean orderMenuContainsCheckSub(String userMenu) {
        return Arrays.asList(Menu.values()).contains(Menu.valueOf(userMenu));
    }
}
