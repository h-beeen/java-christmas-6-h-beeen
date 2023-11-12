package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.PromotionController;
import christmas.controller.VisitDayController;
import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.requestOrders();

        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrdersResult(orders);
        PromotionController.responseAppliedBenefitResult(visitDay, orders);

        Console.close();
    }
}
