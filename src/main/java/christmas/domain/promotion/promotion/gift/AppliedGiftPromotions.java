package christmas.domain.promotion.promotion.gift;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.promotion.constants.Badge;

import java.util.EnumMap;

public class AppliedGiftPromotions {
    private final EnumMap<GiftPromotion, Integer> promotions;

    private AppliedGiftPromotions(
            VisitDay visitDay,
            Orders orders,
            Badge badge
    ) {
        GiftContext giftContext = GiftContext.create(visitDay, orders, badge);
        this.promotions = giftContext.getResult();
    }

    public static AppliedGiftPromotions create(
            VisitDay visitDay,
            Orders orders,
            Badge badge
    ) {
        return new AppliedGiftPromotions(visitDay, orders, badge);
    }


    public int getTotalGiftPrice() {
        return promotions.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getGiftPrice() * entry.getValue())
                .sum();
    }

    public EnumMap<GiftPromotion, Integer> getPromotions() {
        return promotions;
    }
}
