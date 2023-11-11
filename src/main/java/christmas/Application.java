package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.DateController;
import christmas.controller.OrderController;
import christmas.controller.PromotionController;
import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;
import christmas.domain.promotion.constants.Promotion;

import java.util.EnumMap;

public class Application {
    public static void main(String[] args) {
        VisitingDate visitingDate = DateController.requestVisitingDate();
        Order order = OrderController.requestOrder();

        OrderController.responseOrderResult(order);
        OrderController.responseTotalOriginPriceResult(order);

        EnumMap<Promotion, Integer> promotionIntegerEnumMap = PromotionController.applyPromotions(visitingDate, order);
        System.out.println("promotionIntegerEnumMap = " + promotionIntegerEnumMap.get(Promotion.CHRISTMAS_D_DAY_DISCOUNT));
        Console.close();
    }
}
