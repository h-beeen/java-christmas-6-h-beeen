package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.VisitDayController;
import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.discount.DiscountPromotion;

import java.util.EnumMap;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.requestOrders();
        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrdersResult(orders);

        AppliedDiscountPromotions appliedPromotion = AppliedDiscountPromotions.create(visitDay, orders);
        EnumMap<DiscountPromotion, Integer> promotions = appliedPromotion.getPromotions();

        promotions.forEach((key, value) -> System.out.println(key.name() + " : " + value));

        Console.close();
    }
}
