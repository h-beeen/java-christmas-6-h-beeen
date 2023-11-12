package christmas.controller.mapper;

import christmas.controller.dto.GiftResponse;
import christmas.domain.promotion.promotion.gift.AppliedGiftPromotions;

import java.util.Map;
import java.util.stream.Collectors;

public final class GiftResponseMapper implements ResponseMapper<AppliedGiftPromotions, GiftResponse> {
    private static final GiftResponseMapper GIFT_RESPONSE_MAPPER = new GiftResponseMapper();

    private GiftResponseMapper() {
    }

    public static GiftResponseMapper getInstance() {
        return GIFT_RESPONSE_MAPPER;
    }

    @Override
    public GiftResponse toResponse(AppliedGiftPromotions appliedGiftPromotions) {
        Map<String, Integer> discountResult = appliedGiftPromotions.getPromotions()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getGiftName(),
                        Map.Entry::getValue));

        int giftTotalPriceResult = appliedGiftPromotions.getGiftTotalPriceResult();
        return new GiftResponse(discountResult, giftTotalPriceResult);
    }
}
