package christmas.controller.dto;

import christmas.domain.promotion.promotion.gift.AppliedGiftPromotions;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public record GiftResponse(
        Map<String, Integer> giftQuantityResult,
        int giftTotalPrice
) {
    public static GiftResponse from(AppliedGiftPromotions appliedGiftPromotions) {
        Map<String, Integer> discountResult = appliedGiftPromotions.getPromotions()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Entry::getValue));

        final int giftTotalPriceResult = appliedGiftPromotions.getTotalGiftPrice();
        return new GiftResponse(discountResult, giftTotalPriceResult);
    }
}
