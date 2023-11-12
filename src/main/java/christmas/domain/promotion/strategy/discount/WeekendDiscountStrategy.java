package christmas.domain.promotion.strategy.discount;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.strategy.PromotionStrategy;
import christmas.domain.promotion.strategy.constants.Promotion;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import static christmas.domain.order.constants.MenuCategory.MAIN_DISH;
import static christmas.domain.promotion.strategy.constants.Promotion.WEEKEND_DISCOUNT;

public class WeekendDiscountStrategy implements PromotionStrategy {
    private static final int DISCOUNT_PER_MAIN = 2_023;

    private WeekendDiscountStrategy() {
    }

    public static WeekendDiscountStrategy create() {
        return new WeekendDiscountStrategy();
    }

    @Override
    public Entry<Promotion, Integer> apply(
            VisitDay visitDay,
            Orders orders
    ) {
        final int discountAmount = orders.countOrdersByMenuType(MAIN_DISH) * DISCOUNT_PER_MAIN;

        return new SimpleEntry<>(WEEKEND_DISCOUNT, discountAmount);
    }
}
