package christmas.controller.mapper;

import christmas.controller.dto.DiscountResultResponse;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.promotion.discount.DiscountPromotion;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class DiscountResultMapper implements ResponseMapper<AppliedDiscountPromotions, DiscountResultResponse> {
    public static final DiscountResultMapper BENEFIT_RESPONSE_MAPPER = new DiscountResultMapper();

    private DiscountResultMapper() {
    }

    public static DiscountResultMapper getInstance() {
        return BENEFIT_RESPONSE_MAPPER;
    }

    @Override
    public DiscountResultResponse toResponse(AppliedDiscountPromotions appliedPromotions) {
        EnumMap<DiscountPromotion, Integer> result = appliedPromotions.getResult();

        Map<String, Integer> giftPromotionResponse = result.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getPromotionName(),
                        Entry::getValue,
                        (prev, next) -> next)
                );

        return new DiscountResultResponse(giftPromotionResponse);
    }
}