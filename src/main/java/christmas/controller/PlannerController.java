package christmas.controller;

import christmas.controller.input.DateInputController;

public class PlannerController {
    private static final PlannerController plannerController = new PlannerController();
    private final DateInputController dateInputController;

    private PlannerController() {
        this.dateInputController = DateInputController.getInstance();
    }

    public void start() {
        dateInputController.executeRequest();
    }

    public static PlannerController getInstance() {
        return plannerController;
    }
}
