package christmas.controller.mapper;

import christmas.controller.dto.DiscountResponse;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.promotion.discount.DiscountPromotion;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class DiscountResponseMapper
        implements ResponseMapper<AppliedDiscountPromotions, DiscountResponse>, PromotionResponseMapper {
    public static final DiscountResponseMapper BENEFIT_RESPONSE_MAPPER = new DiscountResponseMapper();

    private DiscountResponseMapper() {
    }

    public static DiscountResponseMapper getInstance() {
        return BENEFIT_RESPONSE_MAPPER;
    }

    @Override
    public DiscountResponse from(AppliedDiscountPromotions appliedPromotions) {
        EnumMap<DiscountPromotion, Integer> promotions = appliedPromotions.getPromotions();

        Map<String, Integer> discountResult = promotions.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getPromotionName(),
                        Entry::getValue,
                        (prev, next) -> next)
                );

        final int totalDiscountAmount = appliedPromotions.getTotalDiscountAmount();
        return new DiscountResponse(discountResult, totalDiscountAmount);
    }
}
