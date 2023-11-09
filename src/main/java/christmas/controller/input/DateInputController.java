package christmas.controller.input;

import christmas.domain.Date;
import christmas.domain.Parser;

public class DateInputController extends InputController<Date> {
    private static final DateInputController menuInputController = new DateInputController();

    private DateInputController() {
    }

    public static DateInputController getInstance() {
        return menuInputController;
    }

    @Override
    public Date request() {
        String dateInput = inputReader.readLine();
        int date = Parser.parseDate(dateInput);
        return new Date(date);
    }
}
