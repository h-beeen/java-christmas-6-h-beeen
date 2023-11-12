package christmas.controller;

import christmas.controller.dto.DiscountResponse;
import christmas.controller.dto.GiftResponse;
import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.promotion.constants.Badge;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.promotion.gift.AppliedGiftPromotions;
import christmas.view.OutputWriter;

import static christmas.domain.promotion.promotion.constants.Badge.DEFAULT;
import static christmas.view.constants.ResponseFormat.PRICE_RESULT;
import static christmas.view.constants.ResponseMessage.*;

public class PromotionController {
    private PromotionController() {
    }

    public static void responseAppliedBenefitResult(
            VisitDay visitDay,
            Orders orders
    ) {
        Badge defaultBadge = DEFAULT;
        AppliedDiscountPromotions appliedDiscountPromotions = AppliedDiscountPromotions.create(visitDay, orders, defaultBadge);
        AppliedGiftPromotions appliedGiftPromotions = AppliedGiftPromotions.create(visitDay, orders, defaultBadge);

        final int totalDiscountAmount = appliedDiscountPromotions.getExpectedPayment(orders);

        DiscountResponse discountResponse = DiscountResponse.from(appliedDiscountPromotions);
        GiftResponse giftResponse = GiftResponse.from(appliedGiftPromotions);

        responseGiftResult(giftResponse);
        responseBenefitResult(discountResponse, giftResponse);
        responseTotalBenefitResult(discountResponse, giftResponse);
        responseExpectPaymentResult(totalDiscountAmount);
    }

    private static void responseGiftResult(GiftResponse giftResponse) {
        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_GIFT_RESULT);
        OutputWriter.printGiftQuantityResponse(giftResponse);
    }

    private static void responseBenefitResult(
            DiscountResponse discountResponse,
            GiftResponse giftResponse
    ) {
        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_BENEFIT_RESPONSE);
        OutputWriter.printBenefitResponse(discountResponse, giftResponse);
    }

    private static void responseTotalBenefitResult(
            DiscountResponse discountResponse,
            GiftResponse giftResponse
    ) {
        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_TOTAL_BENEFIT_RESULT);
        OutputWriter.printTotalBenefitResponse(discountResponse, giftResponse);
    }

    private static void responseExpectPaymentResult(final int expectPayment) {
        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_EXPECT_PAYMENT_RESULT);
        OutputWriter.println(PRICE_RESULT.generateFormat(expectPayment)); // 최종 결제금액
    }
}
