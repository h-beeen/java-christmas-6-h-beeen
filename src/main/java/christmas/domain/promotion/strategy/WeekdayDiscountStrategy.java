package christmas.domain.promotion.strategy;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import static christmas.domain.promotion.constants.Promotion.CHRISTMAS_D_DAY_DISCOUNT;

public class WeekdayDiscountStrategy implements PromotionStrategy {
    private static final WeekdayDiscountStrategy WEEKDAY_DISCOUNT_STRATEGY = new WeekdayDiscountStrategy();

    private WeekdayDiscountStrategy() {
    }

    public static WeekdayDiscountStrategy getInstance() {
        return WEEKDAY_DISCOUNT_STRATEGY;
    }

    @Override
    public Entry<Promotion, Integer> apply(
            VisitDay visitDay,
            Orders order
    ) {
        return new SimpleEntry<>(CHRISTMAS_D_DAY_DISCOUNT, discountAmount);
    }
}
