package christmas.view;

import christmas.controller.dto.DiscountResultResponse;
import christmas.controller.dto.OrderResponse;
import christmas.controller.dto.VisitDayResponse;
import christmas.view.constants.ResponseMessage;

import static christmas.view.constants.ResponseFormat.*;

public class OutputWriter {
    private OutputWriter() {
    }

    public static void println(Object object) {
        System.out.println(object);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printMessageResponse(ResponseMessage responseMessage) {
        System.out.println(responseMessage.getMessage());
    }

    public static void printMenuOrdersResponse(OrderResponse orderResponse) {
        orderResponse
                .orderMenus()
                .forEach((menuName, orderQuantity) ->
                        println(ORDERS_RESULT.generateFormat(menuName, orderQuantity)));
    }

    public static void printTotalOriginPriceResponse(final int originPrice) {
        println(TOTAL_ORIGIN_PRICE_RESULT.generateFormat(originPrice));
    }

    public static void printVisitDayResponse(VisitDayResponse visitDayResponse) {
        println(PROMOTION_PREVIEW.generateFormat(visitDayResponse.month(), visitDayResponse.date()));
    }

    public static void printBenefitResponse(DiscountResultResponse giftResponse) {
        giftResponse.discountResult()
                .forEach((key, value) ->
                        OutputWriter.println(BENEFIT_RESULT.generateFormat(key, value)));
    }
}
