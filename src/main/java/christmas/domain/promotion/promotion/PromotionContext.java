package christmas.domain.promotion.promotion;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionContext {
    private final List<Promotion> applicablePromotions;

    private PromotionContext(VisitDay visitDay, Orders orders) {
        this.applicablePromotions = Arrays.stream(Promotion.values())
                .filter(promotion -> promotion.isPromotionPeriod(visitDay))
                .filter(promotion -> promotion.isApplicable(visitDay, orders))
                .toList();
    }

    //== Static Factory Method ==//
    public static PromotionContext create(
            VisitDay visitDay,
            Orders orders
    ) {
        return new PromotionContext(visitDay, orders);
    }

    //== Utility Method ==//
    public EnumMap<Promotion, Integer> applyPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        return applicablePromotions.stream()
                .collect(Collectors.toMap(
                        promotion -> promotion,
                        promotion -> promotion.applyPromotion(visitDay, orders),
                        (previous, next) -> next,
                        () -> new EnumMap<>(Promotion.class)));
    }
}
