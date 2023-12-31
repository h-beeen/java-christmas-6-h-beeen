package christmas.domain.promotion.gift;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.badge.BadgePromotion;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class GiftContext {
    private final List<GiftPromotion> applicablePromotions;

    //== Constructor ==//
    private GiftContext(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        this.applicablePromotions = Arrays.stream(GiftPromotion.values())
                .filter(promotion -> promotion.isPromotionPeriod(visitDay))
                .filter(promotion -> promotion.isApplicable(visitDay, orders, badge))
                .toList();
    }

    //== Static Factory Method ==//
    public static GiftContext create(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        return new GiftContext(visitDay, orders, badge);
    }

    //== Utility Method ==//
    public EnumMap<GiftPromotion, Integer> applyPromotions() {
        return applicablePromotions.stream()
                .collect(Collectors.toMap(
                        promotion -> promotion,
                        GiftPromotion::getQuantity,
                        (previous, next) -> next,
                        () -> new EnumMap<>(GiftPromotion.class)));
    }
}
