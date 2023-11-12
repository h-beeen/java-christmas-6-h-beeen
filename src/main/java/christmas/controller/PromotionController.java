package christmas.controller;

import christmas.controller.dto.DiscountResponse;
import christmas.controller.dto.GiftResponse;
import christmas.controller.mapper.DiscountResponseMapper;
import christmas.controller.mapper.GiftResponseMapper;
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
    private static final DiscountResponseMapper DISCOUNT_RESPONSE_MAPPER = DiscountResponseMapper.getInstance();
    private static final GiftResponseMapper GIFT_RESPONSE_MAPPER = GiftResponseMapper.getInstance();

    public static void responseAppliedBenefitResult(
            VisitDay visitDay,
            Orders orders
    ) {
        Badge defaultBadge = DEFAULT;

        AppliedDiscountPromotions appliedDiscountPromotions = AppliedDiscountPromotions.create(visitDay, orders, defaultBadge);
        AppliedGiftPromotions appliedGiftPromotions = AppliedGiftPromotions.create(visitDay, orders, defaultBadge);

        DiscountResponse discountResponse = DISCOUNT_RESPONSE_MAPPER.from(appliedDiscountPromotions);
        GiftResponse giftResponse = GIFT_RESPONSE_MAPPER.from(appliedGiftPromotions);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_GIFT_RESPONSE);
        OutputWriter.printGiftQuantityResponse(giftResponse);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_BENEFIT_RESPONSE);
        OutputWriter.printBenefitResponse(discountResponse, giftResponse);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_TOTAL_BENEFIT_RESPONSE);
        OutputWriter.printTotalBenefitResponse(discountResponse, giftResponse);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_EXPECT_TOTAL_BENEFIT);
        OutputWriter.println(PRICE_RESULT.generateFormat(orders.calculateTotalOriginPrice() - discountResponse.discountTotalPrice()));
    }
}
