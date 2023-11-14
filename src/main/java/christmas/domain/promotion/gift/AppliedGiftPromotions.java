package christmas.domain.promotion.gift;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.badge.BadgePromotion;
import christmas.domain.promotion.discount.AppliedDiscountPromotions;

import java.util.EnumMap;

public class AppliedGiftPromotions {
    private final EnumMap<GiftPromotion, Integer> promotions;

    //== Constructor ==//
    private AppliedGiftPromotions(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        GiftContext giftContext = GiftContext.create(visitDay, orders, badge);
        this.promotions = giftContext.applyPromotions();
    }

    //== Static Factory Method ==//
    public static AppliedGiftPromotions create(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        return new AppliedGiftPromotions(visitDay, orders, badge);
    }

    //== Utility Method ==//
    public int getTotalGiftPrice() {
        return promotions.keySet()
                .stream()
                .mapToInt(GiftPromotion::calculateGiftPrice)
                .sum();
    }

    public int getTotalBenefit(AppliedDiscountPromotions discountPromotions) {
        return discountPromotions.getTotalDiscountAmount() + getTotalGiftPrice();
    }

    //== Getter (Only permit to use Dto/ResponseMapper) ==//
    public EnumMap<GiftPromotion, Integer> getPromotions() {
        return promotions;
    }
}
