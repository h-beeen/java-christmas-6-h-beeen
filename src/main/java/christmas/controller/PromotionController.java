package christmas.controller;

import christmas.controller.dto.DiscountResultResponse;
import christmas.controller.mapper.BenefitResponseMapper;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.promotion.gift.AppliedGiftPromotions;
import christmas.view.OutputWriter;

import static christmas.view.constants.ResponseMessage.RESPONSE_GIFT_MENU_RESPONSE;

public class PromotionController {
    private static final BenefitResponseMapper DISCOUNT_RESPONSE_MAPPER = BenefitResponseMapper.getInstance();

    private PromotionController() {
    }

    public static void responseBenefitResponse(
            AppliedDiscountPromotions appliedPromotions,
            AppliedGiftPromotions giftPromotions
    ) {
        DiscountResultResponse discountResult = DISCOUNT_RESPONSE_MAPPER.toResponse(appliedPromotions);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_GIFT_MENU_RESPONSE);
        OutputWriter.printBenefitResponse(discountResult);
    }
}
