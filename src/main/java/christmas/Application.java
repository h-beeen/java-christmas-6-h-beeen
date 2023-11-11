package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.VisitDayController;
import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.AppliedPromotions;
import christmas.domain.promotion.constants.Promotion;
import christmas.view.OutputWriter;

import java.util.EnumMap;

import static christmas.domain.promotion.constants.Promotion.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.domain.promotion.constants.Promotion.WEEKDAY_DISCOUNT;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.requestOrders();
        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrdersResult(orders);

        AppliedPromotions appliedPromotion = AppliedPromotions.create(visitDay, orders);
        EnumMap<Promotion, Integer> promotions = appliedPromotion.getPromotions();
        OutputWriter.println("디데이 : " + promotions.get(CHRISTMAS_D_DAY_DISCOUNT));
        OutputWriter.println("평일 : " + promotions.get(WEEKDAY_DISCOUNT));

        Console.close();
    }
}
