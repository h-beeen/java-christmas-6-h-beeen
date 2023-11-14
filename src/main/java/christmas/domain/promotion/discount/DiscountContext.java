package christmas.domain.promotion.discount;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.badge.BadgePromotion;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountContext {
    private final List<DiscountPromotion> applicablePromotions;

    private DiscountContext(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        this.applicablePromotions = Arrays.stream(DiscountPromotion.values())
                .filter(promotion -> promotion.isPromotionPeriod(visitDay))
                .filter(promotion -> promotion.isApplicable(visitDay, orders, badge))
                .toList();
    }

    //== Static Factory Method ==//
    public static DiscountContext create(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        return new DiscountContext(visitDay, orders, badge);
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

    //== Getter (Only permit to use TestCode) ==//
    public List<DiscountPromotion> getApplicablePromotions() {
        return applicablePromotions;
    }
}
