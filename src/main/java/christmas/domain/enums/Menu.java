package christmas.domain.enums;

public enum Menu {

    양송이수프(6000, MenuType.APPETIZER),
    타파스(5500, MenuType.APPETIZER),
    시저샐러드(8000, MenuType.APPETIZER),
    티본스테이크(55000, MenuType.MAIN),
    바비큐립(54000, MenuType.MAIN),
    해산물파스타(35000, MenuType.MAIN),
    크리스마스파스타(25000, MenuType.MAIN),
    초코케이크(15000, MenuType.DESSERT),
    아이스크림(5000, MenuType.DESSERT),
    제로콜라(3000, MenuType.DRINK),
    레드와인(60000, MenuType.DRINK),
    샴페인(25000, MenuType.DRINK);

    private final int price;
    private final MenuType type;

    Menu(int price, MenuType type) {
        this.price = price;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getType() {
        return type;
    }

    public enum MenuType {
        APPETIZER, MAIN, DESSERT, DRINK
    }
}