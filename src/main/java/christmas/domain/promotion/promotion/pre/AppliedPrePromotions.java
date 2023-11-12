package christmas.domain.promotion.promotion.pre;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.EnumMap;
import java.util.Map.Entry;

public class AppliedPrePromotions {
    private final EnumMap<PrePromotion, Integer> prePromotions;

    //== Constructor ==//
    private AppliedPrePromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        PrePromotionContext promotionContext = PrePromotionContext.create(visitDay, orders);
        this.prePromotions = promotionContext.applyPrePromotions(visitDay, orders);
    }

    //== Static Factory Method ==//
    public static AppliedPrePromotions create(
            VisitDay visitDay,
            Orders order
    ) {
        return new AppliedPrePromotions(visitDay, order);
    }

    public int calculateTotalDiscountBenefit() {
        return prePromotions.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isDiscountType())
                .mapToInt(Entry::getValue)
                .sum();
    }

    //== Getter todo 삭제해야 할 Getter (디버깅용) ==//
    public EnumMap<PrePromotion, Integer> getPrePromotions() {
        return prePromotions;
    }
}
