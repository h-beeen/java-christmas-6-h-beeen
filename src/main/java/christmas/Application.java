package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.VisitDayController;
import christmas.domain.order.Order;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.AppliedPromotion;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();

        Order order = OrderController.requestOrder();

        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrderResult(order);
        OrderController.responseTotalOriginPriceResult(order);

        AppliedPromotion appliedPromotion = AppliedPromotion.create(visitDay, order);

        Console.close();
    }
}
