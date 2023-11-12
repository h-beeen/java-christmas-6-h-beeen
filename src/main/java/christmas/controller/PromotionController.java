package christmas.controller;

import christmas.controller.mapper.DiscountResponseMapper;
import christmas.controller.mapper.GiftResponseMapper;

public class PromotionController {
    private static final DiscountResponseMapper DISCOUNT_RESPONSE_MAPPER = DiscountResponseMapper.getInstance();
    private static final GiftResponseMapper GIFT_RESPONSE_MAPPER = GiftResponseMapper.getInstance();

    private PromotionController() {
    }
}
