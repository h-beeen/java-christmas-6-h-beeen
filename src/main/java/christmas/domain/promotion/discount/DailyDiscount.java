package christmas.domain.promotion.discount;

import christmas.domain.promotion.PromotionStrategy;

public class DailyDiscount implements PromotionStrategy {
    private static final DailyDiscount dailyDiscount = new DailyDiscount();

    private DailyDiscount() {
    }

    public static DailyDiscount getInstance() {
        return dailyDiscount;
    }

    @Override
    public int apply() {
        return 0;
    }

    @Override
    public boolean canApplicable() {
        return false;
    }
}
