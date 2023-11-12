package christmas.domain.promotion.promotion.pre;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class PrePromotionContext {
    private final List<PrePromotion> applicablePrePromotions;

    private PrePromotionContext(VisitDay visitDay, Orders orders) {
        this.applicablePrePromotions = Arrays.stream(PrePromotion.values())
                .filter(promotion -> promotion.isPromotionPeriod(visitDay))
                .filter(promotion -> promotion.isApplicable(visitDay, orders))
                .toList();
    }

    //== Static Factory Method ==//
    public static PrePromotionContext create(
            VisitDay visitDay,
            Orders orders
    ) {
        return new PrePromotionContext(visitDay, orders);
    }

    //== Utility Method ==//
    public EnumMap<PrePromotion, Integer> applyPrePromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        return applicablePrePromotions.stream()
                .collect(Collectors.toMap(
                        prePromotion -> prePromotion,
                        prePromotion -> prePromotion.applyPromotion(visitDay, orders),
                        (previous, next) -> next,
                        () -> new EnumMap<>(PrePromotion.class)));
    }
}
