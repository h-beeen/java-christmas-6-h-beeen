package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.DateController;
import christmas.controller.OrderController;
import christmas.controller.PromotionController;
import christmas.domain.VisitingDate;
import christmas.domain.menu.MenuOrders;

public class Application {
    public static void main(String[] args) {
        VisitingDate visitingDate = DateController.requestVisitingDate();
        MenuOrders menuOrders = OrderController.requestOrder();

        OrderController.responseMenuOrderResult(menuOrders);
        OrderController.responseTotalOriginPriceResult(menuOrders);

        PromotionController.applyPromotions(visitingDate, menuOrders);

        Console.close();
    }
}
