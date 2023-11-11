package christmas.view;

import christmas.controller.dto.OrderResponse;
import christmas.view.constants.ResponseMessage;

import static christmas.view.constants.ResponseFormat.ORDER_RESULT;
import static christmas.view.constants.ResponseFormat.TOTAL_ORIGIN_PRICE_RESULT;

public class OutputWriter {
    private OutputWriter() {
    }

    public static void println(Object object) {
        System.out.println(object);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printResponseMessage(ResponseMessage responseMessage) {
        System.out.println(responseMessage.getMessage());
    }

    public static void printMenuOrdersResponse(OrderResponse orderResponse) {
        orderResponse
                .orderMenus()
                .forEach((menuName, orderQuantity) ->
                        println(ORDER_RESULT.generateFormat(menuName, orderQuantity)));
    }

    public static void printTotalOriginPrice(final int originPrice) {
        println(TOTAL_ORIGIN_PRICE_RESULT.generateFormat(originPrice));
    }
}
