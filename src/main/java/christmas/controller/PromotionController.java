package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.MultiplePromotionContext;
import christmas.domain.promotion.constants.Promotion;

import java.util.EnumMap;

public class PromotionController {
    private PromotionController() {
    }

    public static EnumMap<Promotion, Integer> applyPromotions(
            VisitDay visitDay,
            Order order
    ) {
        MultiplePromotionContext promotionContext = MultiplePromotionContext.getInstance();
        return promotionContext.applyPromotion(visitDay, order);
    }
}
