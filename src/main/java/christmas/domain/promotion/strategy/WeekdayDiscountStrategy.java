package christmas.domain.promotion.strategy;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import static christmas.domain.order.constants.MenuCategory.DESSERT;
import static christmas.domain.promotion.constants.Promotion.WEEKDAY_DISCOUNT;

public class WeekdayDiscountStrategy implements PromotionStrategy {
    private static final WeekdayDiscountStrategy WEEKDAY_DISCOUNT_STRATEGY = new WeekdayDiscountStrategy();
    private static final int DISCOUNT_PER_DESSERT = 2023;

    private WeekdayDiscountStrategy() {
    }

    public static WeekdayDiscountStrategy getInstance() {
        return WEEKDAY_DISCOUNT_STRATEGY;
    }

    @Override
    public Entry<Promotion, Integer> apply(
            VisitDay visitDay,
            Orders orders
    ) {
        final int discountAmount = orders.countOrdersByMenuType(DESSERT) * DISCOUNT_PER_DESSERT;

        return new SimpleEntry<>(WEEKDAY_DISCOUNT, discountAmount);
    }
}
