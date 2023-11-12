package christmas.domain.promotion.promotion.gift;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;

import java.util.EnumMap;

public class AppliedGiftPromotions {
    private final EnumMap<GiftPromotion, Integer> promotions;

    private AppliedGiftPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        GiftContext giftContext = GiftContext.create(visitDay, orders);
        this.promotions = giftContext.getResult();
    }

    public static AppliedGiftPromotions create(
            VisitDay visitDay,
            Orders orders
    ) {
        return new AppliedGiftPromotions(visitDay, orders);
    }


    public int getGiftTotalPriceResult() {
        return promotions.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getGiftPrice() * entry.getValue())
                .sum();
    }

    public EnumMap<GiftPromotion, Integer> getPromotions() {
        return promotions;
    }
}
