package christmas.domain.promotion.promotion.gift;

import christmas.domain.customer.Orders;
import christmas.domain.customer.VisitDay;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class GiftContext {
    private final List<GiftPromotion> applicablePromotions;

    private GiftContext(
            VisitDay visitDay,
            Orders orders
    ) {
        this.applicablePromotions = Arrays.stream(GiftPromotion.values())
                .filter(promotion -> promotion.isPromotionPeriod(visitDay))
                .filter(promotion -> promotion.isApplicable(visitDay, orders))
                .toList();
    }

    public static GiftContext create(
            VisitDay visitDay,
            Orders orders
    ) {
        return new GiftContext(visitDay, orders);
    }

    public EnumMap<GiftPromotion, Integer> getResult() {
        return applicablePromotions.stream()
                .collect(Collectors.toMap(
                        promotion -> promotion,
                        GiftPromotion::getQuantity,
                        (previous, next) -> next,
                        () -> new EnumMap<>(GiftPromotion.class)));
    }
}
