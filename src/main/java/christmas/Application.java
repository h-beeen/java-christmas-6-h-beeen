package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.PromotionController;
import christmas.controller.VisitDayController;
import christmas.domain.order.Order;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;

import java.util.EnumMap;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();

        Order order = OrderController.requestOrder();

        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrderResult(order);
        OrderController.responseTotalOriginPriceResult(order);

        EnumMap<Promotion, Integer> appliedPromotions = PromotionController.applyPromotions(visitDay, order);
        
        Console.close();
    }
}
