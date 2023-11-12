package christmas.controller.mapper;

import christmas.controller.dto.DiscountResponse;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.promotion.discount.DiscountPromotion;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class DiscountResponseMapper implements ResponseMapper<AppliedDiscountPromotions, DiscountResponse> {
    public static final DiscountResponseMapper BENEFIT_RESPONSE_MAPPER = new DiscountResponseMapper();

    private DiscountResponseMapper() {
    }

    public static DiscountResponseMapper getInstance() {
        return BENEFIT_RESPONSE_MAPPER;
    }

    @Override
    public DiscountResponse from(AppliedDiscountPromotions appliedPromotions) {
        EnumMap<DiscountPromotion, Integer> result =
                appliedPromotions.getPromotions()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Entry::getKey,
                                Entry::getValue,
                                (previous, next) -> next,
                                () -> new EnumMap<>(DiscountPromotion.class)));

        Map<String, Integer> giftPromotionResponse = result.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getPromotionName(),
                        Entry::getValue,
                        (prev, next) -> next)
                );

        int totalPrice = result.values()
                .stream()
                .mapToInt(benefit -> benefit)
                .sum();

        return new DiscountResponse(giftPromotionResponse, totalPrice);
    }
}
