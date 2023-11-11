package christmas.domain.promotion;

import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;
import christmas.domain.promotion.constants.PromotionTable;

import java.util.List;
import java.util.stream.Stream;

public class MultiplePromotionContext {
    private static final MultiplePromotionContext multiplePromotionContext = new MultiplePromotionContext();
    private final List<PromotionStrategy> promotionStrategies;

    private MultiplePromotionContext() {
        this.promotionStrategies = Stream.of(PromotionTable.values())
                .map(PromotionTable::getDiscountStrategy)
                .toList();
    }

    public static MultiplePromotionContext getInstance() {
        return multiplePromotionContext;
    }

    public int applyDiscount(
            VisitingDate visitingDate,
            Order order
    ) {
        
    }
}
