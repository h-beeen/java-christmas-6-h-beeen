package christmas.domain.promotion.strategy;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PromotionContext {
    private final List<PromotionStrategy> promotionStrategies;

    private PromotionContext(VisitDay visitDay, Orders orders) {
        this.promotionStrategies = Arrays.stream(Promotion.values())
                .filter(promotion -> promotion.isPromotionPeriod(visitDay))
                .filter(promotion -> promotion.isApplicable(visitDay, orders))
                .map(Promotion::getPromotionStrategy)
                .toList();
    }

    public static PromotionContext create(
            VisitDay visitDay,
            Orders orders
    ) {
        return new PromotionContext(visitDay, orders);
    }

    public EnumMap<Promotion, Integer> applyPromotions(
            VisitDay visitDay,
            Orders orders
    ) {
        return promotionStrategies.stream()
                .map(strategy -> strategy.apply(visitDay, orders))
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (previous, next) -> next, // In case of duplicate keys, choose the latter value
                        () -> new EnumMap<>(Promotion.class)));
    }
}
