package christmas.controller;

import christmas.controller.dto.GiftResponse;
import christmas.controller.mapper.GiftResponseMapper;
import christmas.domain.promotion.promotion.AppliedPromotions;
import christmas.view.OutputWriter;

import static christmas.view.constants.ResponseMessage.RESPONSE_GIFT_MENU_RESPONSE;

public class PromotionController {
    private static final GiftResponseMapper GIFT_RESPONSE_MAPPER = GiftResponseMapper.getInstance();

    private PromotionController() {
    }

    public static void responseGiftResponse(AppliedPromotions appliedPromotions) {
        GiftResponse giftResponse = GIFT_RESPONSE_MAPPER.toResponse(appliedPromotions);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_GIFT_MENU_RESPONSE);
        OutputWriter.printGiftResponse(giftResponse);
    }
}
