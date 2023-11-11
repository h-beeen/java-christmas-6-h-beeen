package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;
import christmas.domain.promotion.MultiplePromotionContext;

public class PromotionController {
    private PromotionController() {
    }

    public static void applyPromotions(
            VisitingDate visitingDate,
            Order order
    ) {
        MultiplePromotionContext promotionContext = MultiplePromotionContext.getInstance();
        promotionContext.applyDiscount(visitingDate, order);
    }
}
