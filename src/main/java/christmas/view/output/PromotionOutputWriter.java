package christmas.view.output;

import christmas.controller.dto.BadgeResponse;
import christmas.controller.dto.DiscountResponse;
import christmas.controller.dto.GiftResponse;
import christmas.view.constants.ResponseFormat;

import java.util.Map;

import static christmas.view.constants.ResponseFormat.*;
import static christmas.view.constants.ResponseMessage.RESPONSE_NONEXISTENCE_RESPONSE;

public final class PromotionOutputWriter extends OutputWriter {
    private PromotionOutputWriter() {
    }

    public static void printBenefitResponse(
            DiscountResponse discountResponse,
            GiftResponse giftResponse
    ) {
        if (isNotDiscountRecipient(discountResponse) && isNotGiftRecipient(giftResponse)) {
            printMessageResponse(RESPONSE_NONEXISTENCE_RESPONSE);
            return;
        }

        printDiscountPrice(discountResponse.discountResult());
        if (isGiftRecipient(giftResponse)) {
            printGiftPrice(giftResponse);
        }
    }

    private static void printDiscountPrice(Map<String, Integer> discountResponse) {
        discountResponse
                .forEach((key, value) ->
                        println(ResponseFormat.BENEFIT_PRICE_RESULT.generateFormat(key, value)));
    }

    private static void printGiftPrice(GiftResponse giftResponse) {
        println(GIFT_RESULT.generateFormat(giftResponse.giftTotalPrice()));
    }


    public static void printGiftQuantityResponse(GiftResponse giftResponse) {
        if (isNotGiftRecipient(giftResponse)) {
            printMessageResponse(RESPONSE_NONEXISTENCE_RESPONSE);
        }

        printGiftQuantity(giftResponse.giftQuantityResult());
    }

    private static void printGiftQuantity(
            Map<String, Integer> giftResponse
    ) {
        giftResponse.forEach((giftName, quantity) ->
                println(PRODUCT_QUANTITY_RESULT.generateFormat(giftName, quantity)));
    }


    public static void printTotalBenefitResponse(final int totalBenefitAmount) {
        if (hasZeroBenefit(totalBenefitAmount)) {
            printMessageResponse(RESPONSE_NONEXISTENCE_RESPONSE);
            return;
        }

        printTotalBenefit(totalBenefitAmount);
    }

    private static void printTotalBenefit(int totalBenefitAmount) {
        println(MINUS_PRICE_RESULT.generateFormat(totalBenefitAmount));
    }


    public static void printBadgeResponse(BadgeResponse badgeResponse) {
        println(badgeResponse.badgeName());
    }

    //== Validation Method ==//
    private static boolean hasZeroBenefit(int totalBenefitAmount) {
        return totalBenefitAmount == 0;
    }

    private static boolean isNotDiscountRecipient(DiscountResponse discountResponse) {
        return hasZeroBenefit(discountResponse.discountResult().size());
    }

    private static boolean isNotGiftRecipient(GiftResponse giftResponse) {
        return hasZeroBenefit(giftResponse.giftQuantityResult().size());
    }

    private static boolean isGiftRecipient(GiftResponse giftResponse) {
        return !isNotGiftRecipient(giftResponse);
    }
}
