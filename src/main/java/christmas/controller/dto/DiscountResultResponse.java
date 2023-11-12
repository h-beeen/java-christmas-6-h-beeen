package christmas.controller.dto;

import java.util.Map;

public record DiscountResultResponse(
        Map<String, Integer> discountResult
) {
}
