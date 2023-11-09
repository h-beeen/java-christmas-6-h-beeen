package christmas.controller;

import christmas.domain.VisitingDate;

public class PlannerController {
    private PlannerController() {
    }

    public static void start() {
        VisitingDate visitingDate = DateController.requestDate();
    }
}
