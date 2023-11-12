package christmas.domain.promotion.promotion.discount;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountContext {
    private final List<DiscountPromotion> applicablePromotions;

    private DiscountContext(
            VisitDay visitDay,
            Orders orders
    ) {
        this.applicablePromotions = Arrays.stream(DiscountPromotion.values())
                .filter(promotion -> promotion.isPromotionPeriod(visitDay))
                .filter(promotion -> promotion.isApplicable(visitDay, orders))
                .toList();
    }

    //== Static Factory Method ==//
    public static DiscountContext create(
            VisitDay visitDay,
            Orders orders
    ) {
        return new DiscountContext(visitDay, orders);
    }

    //== Utility Method ==//
    public EnumMap<DiscountPromotion, Integer> applyPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        return applicablePromotions.stream()
                .collect(Collectors.toMap(
                        promotion -> promotion,
                        promotion -> promotion.applyPromotion(visitDay, orders),
                        (previous, next) -> next,
                        () -> new EnumMap<>(DiscountPromotion.class)));
    }
}
