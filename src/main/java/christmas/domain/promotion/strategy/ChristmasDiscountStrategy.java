package christmas.domain.promotion.strategy;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import static christmas.domain.promotion.constants.Promotion.CHRISTMAS_D_DAY_DISCOUNT;

public class ChristmasDiscountStrategy implements PromotionStrategy {
    private static final ChristmasDiscountStrategy CHRISTMAS_DISCOUNT_STRATEGY = new ChristmasDiscountStrategy();
    private static final int DAILY_BONUS = 100;
    private static final int FIXED_BONUS = 900;

    private ChristmasDiscountStrategy() {
    }

    public static ChristmasDiscountStrategy getInstance() {
        return CHRISTMAS_DISCOUNT_STRATEGY;
    }

    @Override
    public Entry<Promotion, Integer> apply(
            VisitDay visitDay,
            Orders order
    ) {
        final int discountAmount = visitDay.multiplyDate(DAILY_BONUS) + FIXED_BONUS;

        return new SimpleEntry<>(CHRISTMAS_D_DAY_DISCOUNT, discountAmount);
    }
}
