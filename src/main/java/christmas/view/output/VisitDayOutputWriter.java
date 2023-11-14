package christmas.view.output;

import christmas.controller.dto.VisitDayResponse;

import static christmas.view.constants.ResponseFormat.PROMOTION_PREVIEW;

public class VisitDayOutputWriter extends OutputWriter {
    public static void printVisitDayResponse(VisitDayResponse visitDayResponse) {
        println(PROMOTION_PREVIEW.generateFormat(visitDayResponse.month(), visitDayResponse.date()));
    }
}
