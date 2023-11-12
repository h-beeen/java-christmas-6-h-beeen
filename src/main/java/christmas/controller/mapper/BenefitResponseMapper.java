package christmas.controller.mapper;

import christmas.controller.dto.BenefitResponse;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.promotion.discount.DiscountPromotion;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class BenefitResponseMapper implements ResponseMapper<AppliedDiscountPromotions, BenefitResponse> {
    public static final BenefitResponseMapper BENEFIT_RESPONSE_MAPPER = new BenefitResponseMapper();

    private BenefitResponseMapper() {
    }

    public static BenefitResponseMapper getInstance() {
        return BENEFIT_RESPONSE_MAPPER;
    }

    @Override
    public BenefitResponse toResponse(AppliedDiscountPromotions appliedPromotions) {
        EnumMap<DiscountPromotion, Integer> result = appliedPromotions.getResult();

        Map<String, Integer> giftPromotionResponse = result.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getPromotionName(),
                        Entry::getValue,
                        (prev, next) -> next)
                );

        return new BenefitResponse(giftPromotionResponse);
    }
}
