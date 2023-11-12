package christmas.domain.promotion.strategy;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import static christmas.domain.promotion.constants.Promotion.SPECIAL_DISCOUNT;

public class SpecialDiscountStrategy implements PromotionStrategy {
    private static final int DISCOUNT_PER_TOTAL_PRICE = 1_000;

    private SpecialDiscountStrategy() {
    }

    public static SpecialDiscountStrategy create() {
        return new SpecialDiscountStrategy();
    }

    @Override
    public Entry<Promotion, Integer> apply(
            VisitDay visitDay,
            Orders orders
    ) {
        return new SimpleEntry<>(SPECIAL_DISCOUNT, DISCOUNT_PER_TOTAL_PRICE);
    }
}
