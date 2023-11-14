package christmas.view.output;

import christmas.controller.dto.OrderResponse;

import static christmas.view.constants.ResponseFormat.PRICE_RESULT;
import static christmas.view.constants.ResponseFormat.PRODUCT_QUANTITY_RESULT;

public class OrderOutputWriter extends OutputWriter {
    private OrderOutputWriter() {
    }

    public static void printMenuOrdersResponse(OrderResponse orderResponse) {
        orderResponse
                .orderMenus()
                .forEach((menuName, orderQuantity) ->
                        OutputWriter.println(PRODUCT_QUANTITY_RESULT.generateFormat(menuName, orderQuantity)));
    }

    public static void printTotalOriginPriceResponse(final int originPrice) {
        OutputWriter.println(PRICE_RESULT.generateFormat(originPrice));
    }
}
