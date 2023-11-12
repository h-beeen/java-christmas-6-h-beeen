package christmas.controller.dto;

import java.util.Map;

public record DiscountResponse(
        Map<String, Integer> discountResult,
        int discountTotalPrice
) {
}
