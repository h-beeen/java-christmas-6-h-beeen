package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;
import christmas.domain.promotion.discount.MultipleDiscountContext;

public class PromotionController {
    private PromotionController() {
    }

    public static void applyPromotions(
            VisitingDate visitingDate,
            Order order
    ) {
        MultipleDiscountContext.applyDiscount(visitingDate, order);
    }
}
