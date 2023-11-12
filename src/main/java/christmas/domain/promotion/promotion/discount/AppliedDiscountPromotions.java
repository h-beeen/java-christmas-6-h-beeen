package christmas.domain.promotion.promotion.discount;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;

import java.util.EnumMap;

public class AppliedDiscountPromotions {
    private final EnumMap<DiscountPromotion, Integer> promotions;

    //== Constructor ==//
    private AppliedDiscountPromotions(
            VisitDay visitDay,
            Orders orders

    ) {
        DiscountContext promotionContext = DiscountContext.create(visitDay, orders);
        this.promotions = promotionContext.applyPromotions(visitDay, orders);
    }

    //== Static Factory Method ==//
    public static AppliedDiscountPromotions create(
            VisitDay visitDay,
            Orders order
    ) {
        return new AppliedDiscountPromotions(visitDay, order);
    }

    public EnumMap<DiscountPromotion, Integer> getPromotions() {
        return promotions;
    }
}
