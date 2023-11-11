package christmas.domain.promotion;

import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;
import christmas.domain.promotion.constants.Promotion;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class MultiplePromotionContext {
    private static final MultiplePromotionContext multiplePromotionContext = new MultiplePromotionContext();
    private final List<PromotionStrategy> promotionStrategies;

    private MultiplePromotionContext() {
        this.promotionStrategies = Arrays.stream(Promotion.values())
                .map(Promotion::getDiscountStrategy)
                .toList();
    }

    public static MultiplePromotionContext getInstance() {
        return multiplePromotionContext;
    }

    public EnumMap<Promotion, Integer> applyPromotion(
            VisitingDate visitingDate,
            Order order
    ) {
        List<Entry<Promotion, Integer>> list = promotionStrategies.stream()
                .filter(strategy -> strategy.canApplicable(visitingDate, order))
                .map(strategy -> strategy.apply(visitingDate, order))
                .toList();

        EnumMap<Promotion, Integer> ret = new EnumMap<>(Promotion.class);
        list.forEach(key -> {
            Promotion key1 = key.getKey();
            Integer value = key.getValue();
            ret.put(key1, value);
        });
        return ret;
    }
}
