package christmas.controller.dto;

import christmas.domain.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.discount.DiscountPromotion;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public record DiscountResponse(
        Map<String, Integer> discountResult,
        int totalDiscountAmount
) {
    public static DiscountResponse from(AppliedDiscountPromotions appliedPromotions) {
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
