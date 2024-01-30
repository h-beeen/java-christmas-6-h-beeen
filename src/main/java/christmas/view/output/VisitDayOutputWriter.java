package christmas.view.output;

import christmas.controller.dto.VisitDayResponse;

import static christmas.view.constants.ResponseFormat.PROMOTION_PREVIEW;


public final class VisitDayOutputWriter extends OutputWriter {
    private VisitDayOutputWriter() {
    }

    public static void printVisitDayResponse(VisitDayResponse visitDayResponse) {
        println(PROMOTION_PREVIEW.generateFormat(visitDayResponse.month(), visitDayResponse.date()));
    }
}
