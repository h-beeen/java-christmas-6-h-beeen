package christmas.controller;

import christmas.domain.MenusOrder;
import christmas.domain.VisitingDate;

public class PlannerController {
    private PlannerController() {
    }

    public static void start() {
        VisitingDate visitingDate = DateController.requestVisitingDate();
        MenusOrder menuOrders = OrderController.requestOrder();
    }
}
