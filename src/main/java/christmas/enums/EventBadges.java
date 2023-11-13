package christmas.enums;

public enum EventBadges {

    FIRST_BADGE(20000, "산타"),
    SECOND_BADGE(10000, "트리"),
    THIRD_BADGE(5000, "별"),
    NONE(0, "없음");

    private final int badgePrice;
    private final String badgeMessage;

    EventBadges(int badgePrice, String badgeMessage) {
        this.badgePrice = badgePrice;
        this.badgeMessage = badgeMessage;
    }

    public int getBadgePrice() {
        return badgePrice;
    }

    public String getBadgeMessage() {
        return badgeMessage;
    }
}
