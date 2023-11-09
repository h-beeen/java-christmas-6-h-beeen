package christmas;

import christmas.controller.PlannerController;

public class Application {
    public static void main(String[] args) {
        PlannerController plannerController = PlannerController.getInstance();
        plannerController.start();
    }
}
