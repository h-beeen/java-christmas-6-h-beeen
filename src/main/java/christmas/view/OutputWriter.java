package christmas.view;

import christmas.controller.dto.DiscountResponse;
import christmas.controller.dto.GiftResponse;
import christmas.controller.dto.OrderResponse;
import christmas.controller.dto.VisitDayResponse;
import christmas.view.constants.ResponseMessage;

import java.util.Map;

import static christmas.view.constants.ResponseFormat.*;
import static christmas.view.constants.ResponseMessage.RESPONSE_NONEXISTENCE_RESPONSE;

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
                        println(PRODUCT_QUANTITY_RESULT.generateFormat(menuName, orderQuantity)));
    }

    public static void printTotalOriginPriceResponse(final int originPrice) {
        println(PRICE_RESULT.generateFormat(originPrice));
    }

    public static void printVisitDayResponse(VisitDayResponse visitDayResponse) {
        println(PROMOTION_PREVIEW.generateFormat(visitDayResponse.month(), visitDayResponse.date()));
    }

    public static void printBenefitResponse(
            DiscountResponse discountResponse,
            GiftResponse giftResponse
    ) {
        if (discountResponse.discountResult().size() == 0 && giftResponse.giftTotalPrice() == 0) {
            OutputWriter.println("없음");
            return;
        }
        discountResponse.discountResult()
                .forEach((key, value) ->
                        OutputWriter.println(BENEFIT_RESULT.generateFormat(key, value)));
        if (giftResponse.giftTotalPrice() != 0) {
            OutputWriter.println(GIFT_RESULT.generateFormat(giftResponse.giftTotalPrice()));
        }
    }

    public static void printGiftQuantityResponse(GiftResponse giftResponse) {
        Map<String, Integer> giftQuantityResult = giftResponse.giftQuantityResult();

        if (giftQuantityResult.size() == 0) {
            OutputWriter.printMessageResponse(RESPONSE_NONEXISTENCE_RESPONSE);
        }
        giftResponse.giftQuantityResult()
                .forEach((giftName, quantity) ->
                        OutputWriter.println(PRODUCT_QUANTITY_RESULT.generateFormat(giftName, quantity)));
    }

    public static void printTotalBenefitResponse(final int totalBenefit) {
        if (totalBenefit == 0) {
            OutputWriter.printMessageResponse(RESPONSE_NONEXISTENCE_RESPONSE);
            return;
        }
        OutputWriter.println(MINUS_PRICE_RESULT.generateFormat(totalBenefit));
    }
}
