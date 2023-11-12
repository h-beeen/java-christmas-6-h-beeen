package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.PromotionController;
import christmas.controller.VisitDayController;
import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.promotion.gift.AppliedGiftPromotions;
import christmas.domain.promotion.promotion.gift.GiftQuantities;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.requestOrders();
        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrdersResult(orders);

        AppliedDiscountPromotions appliedDiscountPromotions = AppliedDiscountPromotions.create(visitDay, orders);
        AppliedGiftPromotions appliedGiftPromotions = AppliedGiftPromotions.create(visitDay, orders);
        GiftQuantities giftQuantities = GiftQuantities.create(visitDay, orders);

        PromotionController.responseBenefitResponse(appliedDiscountPromotions, appliedGiftPromotions);

        Console.close();
    }
}
