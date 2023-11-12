package christmas.domain.promotion.discount;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountPromotionContext {
    private final List<DiscountPromotion> applicableDiscountPromotions;

    /**
     * @param visitDay
     * @param orders
     */
    private DiscountPromotionContext(VisitDay visitDay, Orders orders) {
        this.applicableDiscountPromotions = Arrays.stream(DiscountPromotion.values())
                .filter(promotion -> promotion.isPromotionPeriod(visitDay))
                .filter(promotion -> promotion.isApplicable(visitDay, orders))
                .toList();
    }

    //== Static Factory Method ==//
    public static DiscountPromotionContext create(
            VisitDay visitDay,
            Orders orders
    ) {
        return new DiscountPromotionContext(visitDay, orders);
    }

    //== Utility Method ==//
    public EnumMap<DiscountPromotion, Integer> applyDiscountPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        return applicableDiscountPromotions.stream()
                .collect(Collectors.toMap(
                        promotion -> promotion,
                        promotion -> promotion.applyPromotion(visitDay, orders),
                        (previous, next) -> next,
                        () -> new EnumMap<>(DiscountPromotion.class)));
    }
}
