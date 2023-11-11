package christmas.domain.promotion;

import java.util.List;

public class MultiplePromotionContext {
    private final List<PromotionStrategy> discountStrategies;

    private MultiplePromotionContext(List<PromotionStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }
}
