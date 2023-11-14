package christmas.controller;

import christmas.controller.dto.VisitDayResponse;
import christmas.domain.consumer.VisitDay;
import christmas.domain.utility.Parser;
import christmas.exception.ExceptionHandler;
import christmas.view.input.InputReader;
import christmas.view.output.VisitDayOutputWriter;

import static christmas.view.constants.ResponseMessage.REQUEST_VISITING_DAY;
import static christmas.view.constants.ResponseMessage.WELCOME_MESSAGE;

public class VisitDayController {
    private VisitDayController() {
    }

    public static VisitDay requestVisitDay() {
        VisitDayOutputWriter.printMessageResponse(WELCOME_MESSAGE);
        VisitDayOutputWriter.printMessageResponse(REQUEST_VISITING_DAY);
        return ExceptionHandler.retryOnBusinessException(VisitDayController::createVisitFromInput);
    }

    public static void responseVisitDay(VisitDay visitDay) {
        VisitDayResponse response = VisitDayResponse.from(visitDay);
        VisitDayOutputWriter.printVisitDayResponse(response);
    }

    private static VisitDay createVisitFromInput() {
        String dateInput = InputReader.readInput();
        int parsedDate = Parser.parseDateInput(dateInput);
        return VisitDay.create(parsedDate);
    }
}
