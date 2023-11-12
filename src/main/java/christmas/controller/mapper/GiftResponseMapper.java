package christmas.controller.mapper;

import christmas.controller.dto.GiftResponse;
import christmas.domain.promotion.promotion.AppliedPromotions;
import christmas.domain.promotion.promotion.Promotion;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class GiftResponseMapper implements ResponseMapper<AppliedPromotions, GiftResponse> {
    public static final GiftResponseMapper GIFT_RESPONSE_MAPPER = new GiftResponseMapper();

    private GiftResponseMapper() {
    }

    public static GiftResponseMapper getInstance() {
        return GIFT_RESPONSE_MAPPER;
    }

    @Override
    public GiftResponse toResponse(AppliedPromotions appliedPromotions) {
        EnumMap<Promotion, Integer> giftPromotions = appliedPromotions.getGiftPromotions();

        Map<String, Integer> giftPromotionResponse = giftPromotions.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getPromotionName(),
                        Entry::getValue,
                        (prev, next) -> next)
                );

        return new GiftResponse(giftPromotionResponse);
    }
}
