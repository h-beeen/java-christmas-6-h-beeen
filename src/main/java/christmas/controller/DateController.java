package christmas.controller;

import christmas.domain.Parser;
import christmas.domain.VisitingDate;
import christmas.exception.ExceptionHandler;
import christmas.view.InputReader;
import christmas.view.OutputWriter;

import static christmas.view.ResponseMessage.REQUEST_VISITING_DATE;
import static christmas.view.ResponseMessage.WELCOME_MESSAGE;

public class DateController {
    private DateController() {
    }

    public static VisitingDate requestDate() {
        OutputWriter.printResponseMessage(WELCOME_MESSAGE);
        OutputWriter.printResponseMessage(REQUEST_VISITING_DATE);
        return ExceptionHandler.retryOnBusinessException(DateController::getDate);
    }

    public static VisitingDate getDate() {
        String dateInput = InputReader.readInput();
        int parsedDate = Parser.parseDate(dateInput);
        return VisitingDate.create(parsedDate);
    }
}
