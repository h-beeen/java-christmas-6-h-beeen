package christmas.domain.promotion;

public enum PromotionPeriodConstraint {
    PROMOTION_YEAR(2023),
    PROMOTION_MONTH(12);

    private final int value;

    PromotionPeriodConstraint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
