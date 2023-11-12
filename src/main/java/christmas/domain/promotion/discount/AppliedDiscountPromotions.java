package christmas.domain.promotion.discount;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.EnumMap;

public class AppliedDiscountPromotions {
    private final EnumMap<DiscountPromotion, Integer> discountPromotions;

    //== Constructor ==//
    private AppliedDiscountPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        DiscountPromotionContext promotionContext = DiscountPromotionContext.create(visitDay, orders);
        this.discountPromotions = promotionContext.applyDiscountPromotions(visitDay, orders);
    }

    //== Static Factory Method ==//
    public static AppliedDiscountPromotions create(
            VisitDay visitDay,
            Orders order
    ) {
        return new AppliedDiscountPromotions(visitDay, order);
    }

    //== Getter todo 삭제해야 할 Getter (디버깅용) ==//
    public EnumMap<DiscountPromotion, Integer> getPromotions() {
        return discountPromotions;
    }
}
