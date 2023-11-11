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
    private static final PromotionContext multiplePromotionContext = new PromotionContext();
    private final List<PromotionStrategy> promotionStrategies;

    private PromotionContext() {
        this.promotionStrategies = Arrays.stream(Promotion.values())
                .map(Promotion::getDiscountStrategy)
                .toList();
    }

    public static PromotionContext getInstance() {
        return multiplePromotionContext;
    }

    public EnumMap<Promotion, Integer> applyAvailablePromotion(
            VisitDay visitDay,
            Orders order
    ) {
        List<Entry<Promotion, Integer>> list = generateAppliedPromotionResults(visitDay, order);
        return convertPromotionResults(list);
    }

    private List<Entry<Promotion, Integer>> generateAppliedPromotionResults(VisitDay visitDay, Orders order) {
        return promotionStrategies.stream()
                .filter(strategy -> strategy.canApplicable(visitDay, order))
                .map(strategy -> strategy.apply(visitDay, order))
                .toList();
    }

    private EnumMap<Promotion, Integer> convertPromotionResults(List<Entry<Promotion, Integer>> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (previous, next) -> next, // In case of duplicate keys, choose the latter value
                        () -> new EnumMap<>(Promotion.class)));
    }
}
