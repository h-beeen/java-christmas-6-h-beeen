package christmas.domain.promotion.promotion;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class AppliedPromotions {
    private final EnumMap<Promotion, Integer> promotions;

    //== Constructor ==//
    private AppliedPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        PromotionContext promotionContext = PromotionContext.create(visitDay, orders);
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

    public EnumMap<Promotion, Integer> getGiftPromotions() {
        return promotions.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isGiftType())
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (previous, next) -> next,
                        () -> new EnumMap<>(Promotion.class)));
    }

    //== Getter todo 삭제해야 할 Getter (디버깅용) ==//
    public EnumMap<Promotion, Integer> getPromotions() {
        return promotions;
    }
}
