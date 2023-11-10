package christmas.controller;

import christmas.domain.VisitingDate;
import christmas.domain.menu.MenuOrders;
import christmas.view.OutputWriter;

public class PromotionController {
    private PromotionController() {
    }

    public static void applyPromotions(
            VisitingDate visitingDate,
            MenuOrders menuOrders
    ) {
        int integer = menuOrders.calculateTotalPrice();
        OutputWriter.println("<할인 전 총 주문 금액 : " + integer);
    }
}
