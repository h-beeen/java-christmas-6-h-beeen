package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.PromotionController;
import christmas.controller.VisitDayController;
import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.requestOrders();
        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrdersResult(orders);

        AppliedDiscountPromotions appliedPromotion = AppliedDiscountPromotions.create(visitDay, orders);
        GiftPromo
        PromotionController.responseBenefitResponse(appliedPromotion);

        Console.close();
    }
}
