package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.VisitDayController;
import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.AppliedPromotions;
import christmas.domain.promotion.constants.Promotion;

import java.util.EnumMap;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.requestOrders();
        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrdersResult(orders);

        AppliedPromotions appliedPromotion = AppliedPromotions.create(visitDay, orders);
        EnumMap<Promotion, Integer> promotions = appliedPromotion.getPromotions();


        promotions.forEach((key, value) -> System.out.println(key.name() + " : " + value));

        Console.close();
    }
}
