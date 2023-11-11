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
        PromotionContext promotionContext = PromotionContext.getInstance();
        this.promotions = promotionContext.applyAvailablePromotion(visitDay, orders);
    }

    public static AppliedPromotions create(
            VisitDay visitDay,
            Orders order
    ) {
        return new AppliedPromotions(visitDay, order);
    }
}
