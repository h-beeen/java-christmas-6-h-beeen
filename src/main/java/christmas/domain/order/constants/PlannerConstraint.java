package christmas.domain.order.constants;

public enum PlannerConstraint {
    PROMOTION_YEAR(2023),
    PROMOTION_MONTH(12),
    MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE(10000);

    private final int value;

    PlannerConstraint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
