package christmas.controller.dto;

import java.util.Map;

public record GiftResponse(
        Map<String, Integer> giftResponse
) {
}
