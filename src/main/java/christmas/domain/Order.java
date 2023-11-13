package christmas.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {

    private final LinkedHashMap<String, Integer> userOrders;

    public Order(String[] orders) {
        this.userOrders = new LinkedHashMap<>();
        addUserOrders(orders);
    }

    public void addItem(String itemName, int quantity) {
        userOrders.merge(itemName, quantity, Integer::sum);
    }

    public Map<String, Integer> getUserOrders() {
        return new LinkedHashMap<>(userOrders);
    }

    public void addUserOrders(String[] orders) {
        Arrays.stream(orders)
                .map(order -> order.trim().split("-"))
                .forEach(menuAndQuantity -> addItem(menuAndQuantity[0], Integer.parseInt(menuAndQuantity[1])));
    }

    public int calculateOrderQuantity() {
        return userOrders.values().stream().mapToInt(Integer::intValue).sum();
    }
}