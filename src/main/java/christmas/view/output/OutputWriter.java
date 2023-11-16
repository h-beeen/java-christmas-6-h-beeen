package christmas.view.output;

import christmas.view.constants.ResponseMessage;

sealed class OutputWriter
        permits ErrorOutputWriter, OrderOutputWriter, PromotionOutputWriter, VisitDayOutputWriter {

    OutputWriter() {
    }

    public static void println(Object object) {
        System.out.println(object);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printMessageResponse(ResponseMessage responseMessage) {
        println(responseMessage.getMessage());
    }
}
