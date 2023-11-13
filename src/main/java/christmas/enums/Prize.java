package christmas.enums;

public enum Prize {

    PRODUCT(120000, "샴페인 1개", 25000),
    NONE(0, "없음", 0);

    private final int minProductPrice;
    private final String prizeName;
    private final int price;

    Prize(int minProductPrice, String prizeName, int price) {
        this.minProductPrice = minProductPrice;
        this.prizeName = prizeName;
        this.price = price;
    }

    public int getMinProductPrice() {
        return minProductPrice;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public int getPrice() {
        return price;
    }
}
