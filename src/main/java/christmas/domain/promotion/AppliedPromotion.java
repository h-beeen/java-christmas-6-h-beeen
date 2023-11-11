package christmas.domain.promotion;

import christmas.domain.order.Order;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;
import christmas.domain.promotion.strategy.PromotionContext;

import java.util.EnumMap;

public class AppliedPromotion {
    private final EnumMap<Promotion, Integer> convertResultToEnumMap;

    private AppliedPromotion(
            VisitDay visitDay,
            Order order
    ) {
        PromotionContext promotionContext = PromotionContext.getInstance();
        this.convertResultToEnumMap = promotionContext.applyPromotion(visitDay, order);
    }

    public static AppliedPromotion create(
            VisitDay visitDay,
            Order order
    ) {
        return new AppliedPromotion(visitDay, order);
    }
}
