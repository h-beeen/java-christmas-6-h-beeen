package christmas.domain.promotion.discount;

import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;
import christmas.domain.promotion.PromotionStrategy;
import christmas.domain.promotion.constants.Promotion;

import java.util.AbstractMap;
import java.util.Map.Entry;

import static christmas.domain.promotion.constants.Promotion.CHRISTMAS_D_DAY_DISCOUNT;

public class ChristmasDiscountStrategy implements PromotionStrategy {
    private static final ChristmasDiscountStrategy christmasDiscountStrategy = new ChristmasDiscountStrategy();

    private ChristmasDiscountStrategy() {
    }

    public static ChristmasDiscountStrategy getInstance() {
        return christmasDiscountStrategy;
    }

    @Override
    public Entry<Promotion, Integer> apply(
            VisitingDate visitingDate,
            Order order
    ) {
        int discountAmount = visitingDate.multiplyDate(100) + 900;
        return new AbstractMap.SimpleEntry<>(CHRISTMAS_D_DAY_DISCOUNT, discountAmount);
    }

    @Override
    public boolean canApplicable(
            VisitingDate visitingDate,
            Order order
    ) {
        return CHRISTMAS_D_DAY_DISCOUNT.isApplicablePromotion(visitingDate);
    }


}
