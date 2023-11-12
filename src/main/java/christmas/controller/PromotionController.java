package christmas.controller;

import christmas.controller.dto.DiscountResultResponse;
import christmas.controller.mapper.DiscountResultMapper;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.promotion.gift.AppliedGiftPromotions;
import christmas.view.OutputWriter;

import static christmas.view.constants.ResponseMessage.RESPONSE_GIFT_RESPONSE;

public class PromotionController {
    private static final DiscountResultMapper DISCOUNT_RESPONSE_MAPPER = DiscountResultMapper.getInstance();

    private PromotionController() {
    }

    public static void responseBenefitResponse(
            AppliedDiscountPromotions appliedPromotions,
            AppliedGiftPromotions giftPromotions
    ) {
        DiscountResultResponse discountResult = DISCOUNT_RESPONSE_MAPPER.toResponse(appliedPromotions);
        int giftTotalPriceResult = giftPromotions.getGiftTotalPriceResult();

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_GIFT_RESPONSE);
        

    }
}
