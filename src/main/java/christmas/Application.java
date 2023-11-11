package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.DateController;
import christmas.controller.OrderController;
import christmas.controller.PromotionController;
import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;

public class Application {
    public static void main(String[] args) {
        VisitingDate visitingDate = DateController.requestVisitingDate();
        Order order = OrderController.requestOrder();

        OrderController.responseOrderResult(order);
        OrderController.responseTotalOriginPriceResult(order);

        PromotionController.applyPromotions(visitingDate, order);

        Console.close();
    }
}
