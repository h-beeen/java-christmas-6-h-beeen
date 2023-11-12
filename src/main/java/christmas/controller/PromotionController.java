package christmas.controller;

import christmas.controller.dto.BenefitResponse;
import christmas.controller.mapper.BenefitResponseMapper;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.view.OutputWriter;

import static christmas.view.constants.ResponseMessage.RESPONSE_GIFT_MENU_RESPONSE;

public class PromotionController {
    private static final BenefitResponseMapper GIFT_RESPONSE_MAPPER = BenefitResponseMapper.getInstance();

    private PromotionController() {
    }

    public static void responseGiftResponse(AppliedDiscountPromotions appliedPromotions) {
        BenefitResponse giftResponse = GIFT_RESPONSE_MAPPER.toResponse(appliedPromotions);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_GIFT_MENU_RESPONSE);
        OutputWriter.printGiftResponse(giftResponse);
    }
}
