package christmas.controller;

import christmas.domain.VisitingDate;
import christmas.exception.ExceptionHandler;
import christmas.utility.Parser;
import christmas.view.InputReader;
import christmas.view.OutputWriter;

import static christmas.view.ResponseMessage.REQUEST_VISITING_DATE;
import static christmas.view.ResponseMessage.WELCOME_MESSAGE;

public class DateController {
    private DateController() {
    }

    public static VisitingDate requestVisitingDate() {
        OutputWriter.printResponseMessage(WELCOME_MESSAGE);
        OutputWriter.printResponseMessage(REQUEST_VISITING_DATE);
        return ExceptionHandler.retryOnBusinessException(DateController::createVisitingDateFromInput);
    }

    private static VisitingDate createVisitingDateFromInput() {
        String dateInput = InputReader.readInput();
        int parsedDate = Parser.parseDateInput(dateInput);
        return VisitingDate.create(parsedDate);
    }
}
