package christmas.domain.promotion.promotion.discount;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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

    public int calculateTotalDiscountBenefit() {
        return promotions.values()
                .stream()
                .mapToInt(benefit -> benefit)
                .sum();
    }

    public EnumMap<DiscountPromotion, Integer> getResult() {
        return promotions.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (previous, next) -> next,
                        () -> new EnumMap<>(DiscountPromotion.class)));
    }
}
