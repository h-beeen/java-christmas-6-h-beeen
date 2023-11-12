package christmas.domain.promotion.gift;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.badge.BadgePromotion;
import christmas.domain.promotion.discount.AppliedDiscountPromotions;

import java.util.EnumMap;

public class AppliedGiftPromotions {
    private final EnumMap<GiftPromotion, Integer> promotions;

    private AppliedGiftPromotions(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        GiftContext giftContext = GiftContext.create(visitDay, orders, badge);
        this.promotions = giftContext.getResult();
    }

    public static AppliedGiftPromotions create(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        return new AppliedGiftPromotions(visitDay, orders, badge);
    }


    public int getTotalGiftPrice() {
        return promotions.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getGiftPrice() * entry.getValue())
                .sum();
    }

    public int getTotalBenefit(AppliedDiscountPromotions discountPromotions) {
        return discountPromotions.getTotalDiscountAmount() + getTotalGiftPrice();
    }

    public EnumMap<GiftPromotion, Integer> getPromotions() {
        return promotions;
    }
}