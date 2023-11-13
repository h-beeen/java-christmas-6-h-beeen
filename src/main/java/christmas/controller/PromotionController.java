package christmas.controller;

import christmas.controller.dto.BadgeResponse;
import christmas.controller.dto.DiscountResponse;
import christmas.controller.dto.GiftResponse;
import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.badge.BadgeContext;
import christmas.domain.promotion.badge.BadgePromotion;
import christmas.domain.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.gift.AppliedGiftPromotions;
import christmas.view.out.PromotionOutputWriter;

import static christmas.domain.promotion.badge.BadgePromotion.DEFAULT;
import static christmas.view.constants.ResponseFormat.PRICE_RESULT;
import static christmas.view.constants.ResponseMessage.*;

public class PromotionController {
    private PromotionController() {
    }

    public static void responseAppliedBenefitResult(
            VisitDay visitDay,
            Orders orders
    ) {
        BadgePromotion defaultBadge = DEFAULT;
        AppliedDiscountPromotions discountPromotions = AppliedDiscountPromotions.create(visitDay, orders, defaultBadge);
        AppliedGiftPromotions giftPromotions = AppliedGiftPromotions.create(visitDay, orders, defaultBadge);
        BadgePromotion badge = BadgeContext.applyPromotions(visitDay, discountPromotions, giftPromotions);

        final int totalDiscountAmount = discountPromotions.getExpectedPayment(orders);
        final int totalBenefit = giftPromotions.getTotalBenefit(discountPromotions);

        DiscountResponse discountResponse = DiscountResponse.from(discountPromotions);
        GiftResponse giftResponse = GiftResponse.from(giftPromotions);
        BadgeResponse badgeResponse = BadgeResponse.from(badge);

        responseGiftResult(giftResponse);
        responseBenefitResult(discountResponse, giftResponse);
        responseTotalBenefitResult(totalBenefit);
        responseExpectPaymentResult(totalDiscountAmount);
        responseBadgeResult(badgeResponse);
    }

    private static void responseGiftResult(GiftResponse giftResponse) {
        PromotionOutputWriter.printNewLine();
        PromotionOutputWriter.printMessageResponse(RESPONSE_GIFT_RESULT);
        PromotionOutputWriter.printGiftQuantityResponse(giftResponse);
    }

    private static void responseBenefitResult(
            DiscountResponse discountResponse,
            GiftResponse giftResponse
    ) {
        PromotionOutputWriter.printNewLine();
        PromotionOutputWriter.printMessageResponse(RESPONSE_BENEFIT_RESPONSE);
        PromotionOutputWriter.printBenefitResponse(discountResponse, giftResponse);
    }

    private static void responseTotalBenefitResult(final int totalBenefit) {
        PromotionOutputWriter.printNewLine();
        PromotionOutputWriter.printMessageResponse(RESPONSE_TOTAL_BENEFIT_RESULT);
        PromotionOutputWriter.printTotalBenefitResponse(totalBenefit);
    }

    private static void responseExpectPaymentResult(final int expectPayment) {
        PromotionOutputWriter.printNewLine();
        PromotionOutputWriter.printMessageResponse(RESPONSE_EXPECT_PAYMENT_RESULT);
        PromotionOutputWriter.println(PRICE_RESULT.generateFormat(expectPayment)); // 최종 결제금액
    }

    private static void responseBadgeResult(BadgeResponse badgeResponse) {
        PromotionOutputWriter.printNewLine();
        PromotionOutputWriter.printMessageResponse(RESPONSE_BADGE_RESULT);
        PromotionOutputWriter.printBadgeResponse(badgeResponse);
    }
}
