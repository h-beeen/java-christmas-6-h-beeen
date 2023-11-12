package christmas.domain.promotion.strategy.discount;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.strategy.PromotionStrategy;
import christmas.domain.promotion.strategy.constants.Promotion;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import static christmas.domain.promotion.strategy.constants.Promotion.CHRISTMAS_D_DAY_DISCOUNT;

public class ChristmasDiscountStrategy implements PromotionStrategy {
    private static final int DAILY_BONUS = 100;
    private static final int FIXED_BONUS = 900;

    private ChristmasDiscountStrategy() {
    }

    public static ChristmasDiscountStrategy create() {
        return new ChristmasDiscountStrategy();
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
