package christmas.domain.promotion;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;
import christmas.domain.promotion.strategy.PromotionContext;

import java.util.EnumMap;

public class AppliedPromotions {
    private final EnumMap<Promotion, Integer> promotions;

    private AppliedPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        PromotionContext promotionContext = PromotionContext.create(visitDay, orders);
        this.promotions = promotionContext.applyPromotions(visitDay, orders);
    }

    public static AppliedPromotions create(
            VisitDay visitDay,
            Orders order
    ) {
        return new AppliedPromotions(visitDay, order);
    }
}