package christmas.domain.promotion.promotion;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.EnumMap;
import java.util.Map.Entry;

public class AppliedPromotions {
    private final EnumMap<Promotion, Integer> promotions;

    //== Constructor ==//
    private AppliedPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        PRomotionContext promotionContext = PRomotionContext.create(visitDay, orders);
        this.promotions = promotionContext.applyPromotions(visitDay, orders);
    }

    //== Static Factory Method ==//
    public static AppliedPromotions create(
            VisitDay visitDay,
            Orders order
    ) {
        return new AppliedPromotions(visitDay, order);
    }

    public int calculateTotalDiscountBenefit() {
        return promotions.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isDiscountType())
                .mapToInt(Entry::getValue)
                .sum();
    }

    //== Getter todo 삭제해야 할 Getter (디버깅용) ==//
    public EnumMap<Promotion, Integer> getPromotions() {
        return promotions;
    }
}
