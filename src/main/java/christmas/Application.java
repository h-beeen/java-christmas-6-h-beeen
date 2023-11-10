package christmas;

import christmas.controller.DateController;
import christmas.controller.OrderController;
import christmas.domain.MenuOrders;
import christmas.domain.VisitingDate;

public class Application {
    public static void main(String[] args) {
        VisitingDate visitingDate = DateController.requestVisitingDate();
        MenuOrders menuOrders = OrderController.requestOrder();
    }
}
