package christmas.controller.dto;

import java.util.Map;

public record OrderResponse(
        Map<String, Integer> orderMenus
) {
}
