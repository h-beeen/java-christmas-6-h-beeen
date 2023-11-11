package christmas.domain.promotion;

import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;
import christmas.domain.promotion.constants.Promotion;
import christmas.domain.promotion.discount.ChristmasDiscountStrategy;

import java.util.List;
import java.util.Map.Entry;

public class MultiplePromotionContext {
    private final List<PromotionStrategy> promotionStrategies;

    public MultiplePromotionContext() {
        this.promotionStrategies = List.of(ChristmasDiscountStrategy.getInstance());
    }

    public List<Entry<Promotion, Integer>> applyPromotion(
            VisitingDate visitingDate,
            Order order
    ) {
        return promotionStrategies.stream()
                .filter(strategy -> strategy.canApplicable(visitingDate, order))
                .map(strategy -> strategy.apply(visitingDate, order))
                .toList();
    }
}
