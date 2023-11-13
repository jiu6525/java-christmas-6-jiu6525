package christmas.exception.orderException;

import java.util.Arrays;

public class EachOrderException {
    public void eachOrderCheck(String[] orders) {
        Arrays.stream(orders)
                .map(order -> order.trim().split("-"))
                .peek(this::orderFormatCheckMain)
                .map(menuAndQuantity -> menuAndQuantity[0]);
    }

    private void orderFormatCheckMain(String[] menuAndQuantity) {
        if (!orderFormatCheckSub(menuAndQuantity)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean orderFormatCheckSub(String[] menuAndQuantity) {
        return menuAndQuantity.length == 2 && menuAndQuantity[1].matches("\\d+");
    }

}
