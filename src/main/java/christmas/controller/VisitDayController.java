package christmas.controller;

import christmas.controller.dto.VisitDayResponse;
import christmas.controller.mapper.VisitDayMapper;
import christmas.domain.order.VisitDay;
import christmas.domain.utility.Parser;
import christmas.exception.ExceptionHandler;
import christmas.view.InputReader;
import christmas.view.OutputWriter;

import static christmas.view.constants.ResponseMessage.REQUEST_VISITING_DAY;
import static christmas.view.constants.ResponseMessage.WELCOME_MESSAGE;

public class VisitDayController {
    private static final VisitDayMapper visitDayMapper = VisitDayMapper.getInstance();

    public static VisitDay requestVisitDay() {
        OutputWriter.printMessageResponse(WELCOME_MESSAGE);
        OutputWriter.printMessageResponse(REQUEST_VISITING_DAY);
        return ExceptionHandler.retryOnBusinessException(VisitDayController::createVisitFromInput);
    }

    public static void responseVisitDay(VisitDay visitDay) {
        VisitDayResponse response = visitDayMapper.toResponse(visitDay);
        OutputWriter.printVisitDayResponse(response);
    }

    private static VisitDay createVisitFromInput() {
        String dateInput = InputReader.readInput();
        int parsedDate = Parser.parseDateInput(dateInput);
        return VisitDay.create(parsedDate);
    }
}
