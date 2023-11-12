package christmas.domain.promotion.promotion.gift;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.EnumMap;

public class AppliedGiftPromotions {
    private final EnumMap<GiftPromotion, Integer> promotions;

    private AppliedGiftPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        GiftContext giftContext = GiftContext.create(visitDay, orders);
        this.promotions = giftContext.generatePriceResult();
    }

    public static AppliedGiftPromotions create(
            VisitDay visitDay,
            Orders orders
    ) {
        return new AppliedGiftPromotions(visitDay, orders);
    }
}
